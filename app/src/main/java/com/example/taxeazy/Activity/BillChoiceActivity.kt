package com.example.taxeazy.Activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taxeazy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.UUID

class BillChoiceActivity : ComponentActivity() {
    private lateinit var editTextBillAmount: EditText
    private lateinit var buttonTakeImage: Button
    private lateinit var buttonSubmit: Button
    private var formattedDate: String = ""
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bill_choice)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        formattedDate = intent.getStringExtra("formattedDate").toString()
        // Initialize views
        editTextBillAmount = findViewById(R.id.editTextBillAmount)
        buttonTakeImage = findViewById(R.id.buttonTakeImage)
        buttonSubmit = findViewById(R.id.submitbtn)

        // Set click listener for the button
        buttonTakeImage.setOnClickListener {
            resultLauncher.launch("image/*")
        }

        // Set click listener for the submit button
        buttonSubmit.setOnClickListener {
            // Here you can handle the submit action if needed
            val billAmount = editTextBillAmount.text.toString()

            // Check if bill amount is empty or exceeds 10 digits
            if (billAmount.isEmpty() || billAmount.length > 10) {
                if (billAmount.isEmpty()) {
                    editTextBillAmount.error = "Please enter the bill amount"
                } else {
                    editTextBillAmount.error = "Bill amount should be less than 10 digits"
                }
            }
            uploadImage(billAmount)
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }

    private fun uploadImage(billAmount: String?) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val imageuid = UUID.randomUUID()
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$uid/$formattedDate/$imageuid")

        imageUri?.let { uri ->
            val inputStream = this.contentResolver.openInputStream(uri)
            inputStream?.use { stream ->
                val bitmap = BitmapFactory.decodeStream(stream)

                // Compress the bitmap
                val compressedBitmap = compressImage(bitmap)

                // Convert the compressed bitmap to a byte array
                val baos = ByteArrayOutputStream()
                compressedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
                val imageData: ByteArray = baos.toByteArray()

                // Upload the compressed image
                storageRef.putBytes(imageData).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // If the upload is successful, get the download URL
                        storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            // Save the download URL and bill amount in Firestore
                            saveBillData(uid, downloadUri.toString(), billAmount, imageuid)
                            Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener { exception ->
                            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun saveBillData(uid: String?, imageUrl: String, billAmount: String?,  imageuid: UUID) {
        val firestore = FirebaseFirestore.getInstance()
        val billData = hashMapOf(
            "imageUrl" to imageUrl,
            "billAmount" to billAmount
        )

        uid?.let {
            val billsRef = firestore.collection("users").document(it)
                .collection("bills").document(formattedDate)
                .collection(if (imageUri != null) "$imageuid" else "${UUID.randomUUID()}")

            billsRef.add(billData)
                .addOnSuccessListener { documentReference ->
                    // Successfully saved bill data in Firestore
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }
        }
    }


    private fun compressImage(bitmap: Bitmap): Bitmap {
        val maxSize = 1024 // Maximum size in kilobytes

        var width = bitmap.width
        var height = bitmap.height

        // Calculate the ratio to maintain the original aspect ratio
        val ratio: Double
        if (width > height) {
            if (width > maxSize) {
                ratio = width.toDouble() / maxSize
                width = maxSize
                height = (height / ratio).toInt()
            }
        } else {
            if (height > maxSize) {
                ratio = height.toDouble() / maxSize
                height = maxSize
                width = (width / ratio).toInt()
            }
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

}