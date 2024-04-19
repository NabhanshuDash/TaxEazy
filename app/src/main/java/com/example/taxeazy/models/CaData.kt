package com.example.taxeazy.models

import com.google.firebase.firestore.FirebaseFirestore

data class CaData (
    val username: String,
    val uin: String,
    val email: String,
    val password: String,
    val status: Boolean,
    val mobileNo: String,
    val location: Double,
    val language: String,
    val currentApplication: List<String> = listOf(),
    val notify: List<String> = listOf(),
    val reported: List<String> = listOf()
)

fun createCA(ca : CaData, db : FirebaseFirestore) {

    val data = mapOf(
        "uid" to generateRandomId(),
        "name" to ca.username,
        "uin" to ca.uin,
        "email" to ca.email,
        "password" to encryptPassword(ca.password),
        "status" to ca.status,
        "mobile" to ca.mobileNo,
        "location" to getCurrentLocation(),
        "language" to ca.language,
        "aid" to ca.currentApplication,
        "notification" to ca.notify,
        "reported" to ca.reported
    )
    db.collection("ca")
        .add(data)
        .addOnSuccessListener { documentReference ->
            println("CA added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding CA: $e")
        }
}