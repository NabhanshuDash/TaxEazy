package com.example.taxeazy.components

import android.net.Uri
import androidx.compose.runtime.Composable
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

private val firestore = FirebaseFirestore.getInstance()
private val storage = FirebaseStorage.getInstance()
private val storageRef: StorageReference = storage.reference
@Composable
fun UploadDocs(applicationId: String, fileUri: Uri) {
    val fileName = UUID.randomUUID().toString()
    val fileRef = storageRef.child("docs/$fileName")
    fileRef.putFile(fileUri)
        .addOnSuccessListener { taskSnapshot ->
            // Get the uploaded file's URI
            fileRef.downloadUrl.addOnSuccessListener { uri ->
                val docData = hashMapOf(
                    "docs" to uri.toString()
                )

                // Update the Firestore document with the file URI
                firestore.collection("application").document(applicationId)
                    .update(docData)
                    .addOnSuccessListener {
                        onSuccess.invoke()
                    }
                    .addOnFailureListener { e ->
                        onFailure.invoke(e)
                    }
            }
        }
        .addOnFailureListener { e ->
            onFailure.invoke(e)
        }
}

private fun uploadDocumentToFirestore(applicationId: String, uri: Uri?) {
    if (uri != null) {
        val firestore = FirebaseFirestore.getInstance()
        val docData = hashMapOf(
            "docsuri" to uri.toString()
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