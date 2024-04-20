package com.example.taxeazy.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taxeazy.adapters.ImageAdapter
import com.example.taxeazy.databinding.FragmentMyBillsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

class MyBillsFragment : Fragment() {

    private lateinit var binding: FragmentMyBillsBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapter: ImageAdapter
    private var imageUri: Uri? = null
    private var selectedDate = Calendar.getInstance()
    private var formattedDate: String=""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyBillsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the CalendarView listener
        val dateFormat = SimpleDateFormat("ddMMyy", Locale.getDefault())
        formattedDate = dateFormat.format(selectedDate.time)
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            formattedDate = dateFormat.format(selectedDate.time)
            // Clear the list and then call getImages()
            mList.clear()
            adapter.notifyDataSetChanged()
            getImages()
        }
        initVars()
        getImages()
        binding.fabAddImage.setOnClickListener {
            resultLauncher.launch("image/*")
            uploadImage()
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }

    private fun uploadImage() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$uid/$formattedDate/${UUID.randomUUID()}")

        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        Toast.makeText(requireContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show()
                        // Optionally, you can perform any action after successful upload
                    }.addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), exception.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ImageAdapter(mList)
        binding.recyclerView.adapter = adapter
    }

    private fun getImages() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val storageRef = FirebaseStorage.getInstance().reference
            .child("images/$uid/$formattedDate")

        storageRef.listAll().addOnSuccessListener { result ->
            for (fileRef in result.items) {
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    mList.add(uri.toString())
                    adapter.notifyDataSetChanged()
                }.addOnFailureListener { exception ->
                    // Handle any errors with getting the download URL
                }
            }
        }.addOnFailureListener { exception ->
            // Handle any errors with listing files
        }
    }

}
