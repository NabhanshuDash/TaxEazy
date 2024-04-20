package com.example.taxeazy.models

import android.location.Location
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
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

suspend fun loginUser(username: String, password: String, db: FirebaseFirestore): Boolean {
    try {
        val querySnapshot: QuerySnapshot = db.collection("users")
            .whereEqualTo("name", username)
            .whereEqualTo("password", encryptPassword(password)) // Assuming you have a function to encrypt password
            .get()
            .await()

        // Check if any user with matching credentials exists
        if (!querySnapshot.isEmpty) {
            // User with matching credentials found
            return true
        }
    } catch (e: Exception) {
        println("Error logging in user: $e")
    }
    // User not found or error occurred
    return false
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


