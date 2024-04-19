package com.example.taxeazy.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

data class NotifyData(
    val notifyId: String,
    val msg: String,
    val status: Boolean
)

fun createNotification(notify: NotifyData, db : FirebaseFirestore) {

    val data = mapOf(
        "uid" to generateRandomNotifyId(),
        "msg" to notify.msg,
        "status" to false
    )

    db.collection("notification")
        .add(data)
        .addOnSuccessListener { documentReference ->
            println("Notification added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding user document: $e")
        }
}

fun generateRandomNotifyId() : String {
    return UUID.randomUUID().toString()
}