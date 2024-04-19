package com.example.taxeazy.models

import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

data class ApplicationData(
    val currentDocs: List<String> = listOf(),
    val currentCA: String,
    val status: String,
    val record: String,
    val payment: Boolean
)

fun createApplication(application: ApplicationData , db: FirebaseFirestore){

    val data = mapOf(
        "uid" to generateRandomId(),
        "caid" to application.currentCA,
        "current" to application.currentDocs,
        "payment" to application.payment,
        "record" to application.record,
        "status" to application.status
    )

    db.collection("application")
        .add(data)
        .addOnSuccessListener { documentReference ->
            println("Application added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding user application: $e")
        }
}