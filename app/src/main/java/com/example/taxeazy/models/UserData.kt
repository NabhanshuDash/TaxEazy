package com.example.taxeazy.models

import android.location.Location
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import java.util.*

data class UserData(
    var username: String,
    var email: String,
    val password: String,
    val mobileNo: String,
    val aadhaarNo: String,
    val businessName: String,
    val language: String,
    val applicationId: List<String> = listOf(),
    val personalStore: List<String> = listOf(),
    val notify: List<String> = listOf(),
    val reported: List<String> = listOf(),
    val location: GeoPoint
)

fun createUser(user: UserData, db: FirebaseFirestore) {

    val data = mapOf(
        "userId" to generateRandomId(),
        "name" to user.username,
        "email" to user.email,
        "password" to encryptPassword(user.password),
        "mobile" to user.mobileNo,
        "aadhaar" to user.aadhaarNo,
        "businessname" to user.businessName,
        "language" to user.language,
        "location" to getCurrentLocation(), // Assuming getCurrentLocation() returns a Location object
        "appid" to user.applicationId,
        "notid" to user.notify,
        "report" to user.reported,
        "storage" to user.personalStore
    )

    db.collection("users")
        .add(data)
        .addOnSuccessListener { documentReference ->
            println("User document added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding user document: $e")
        }
}


fun encryptPassword(password: String): String {
    // Implement password encryption logic
    return password
}

fun generateRandomId(): String {
    // Generate a random user ID (you can use UUID for this)
    return UUID.randomUUID().toString()
}

fun getCurrentLocation(): Location {
    // Implement logic to get current location
    return Location("dummy_provider").apply {
        latitude = 0.0
        longitude = 0.0
    }
}
