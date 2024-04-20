package com.example.taxeazy.Activity

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.taxeazy.components.TextComponent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class UploadDocsActivity : ComponentActivity() {

    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var currentApplicationId: String

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Handle the returned Uri here
            uri?.let {
                uploadDocumentToFirestore(currentApplicationId, it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Extract the applicationId from your intent or wherever you're getting it from
        currentApplicationId = intent.getStringExtra("applicationId") ?: ""

        setContent {
            var documentUri by remember { mutableStateOf<Uri?>(null) }

            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextComponent(
                        value = "Upload Documents",
                        fontStyletxt = FontStyle.Normal,
                        fweight = FontWeight.Bold,
                        size = 24,
                        minheight = 0,
                        colortxt = Color.Black,
                        alignment = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // Launch the file picker
                            getContent.launch("application/pdf")
                        }
                    ) {
                        Text("Select PDF Document")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    documentUri?.let { uri ->
                        Text("Selected Document: $uri")
                    }
                }
            }
        }
    }

    private fun uploadDocumentToFirestore(applicationId: String, uri: Uri) {
        val docData = hashMapOf(
            "docs" to uri.toString()
        )

        firestore.collection("yourCollectionName") // Replace with your collection name
            .document(applicationId)
            .set(docData)
            .addOnSuccessListener {
                // Document uploaded successfully
                println("Document uploaded successfully")
            }
            .addOnFailureListener { e ->
                // Handle error
                println("Error uploading document: $e")
            }
    }
}
