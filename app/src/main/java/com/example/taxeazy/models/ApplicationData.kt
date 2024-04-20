package com.example.taxeazy.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

data class ApplicationData(
    val currentDocs: List<String>,
    val currentCA: String,
    val status: Boolean,
    val record: String,
    val payment: Boolean?,
    val date: Timestamp,
    val uid: String
)

fun createApplication(application: ApplicationData , db: FirebaseFirestore){

    val data = mapOf(
        "uid" to generateRandomId(),
        "caid" to application.currentCA,
        "current" to application.currentDocs,
        "payment" to application.payment,
        "record" to application.record,
        "status" to application.status,
        "date" to Timestamp.now()
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