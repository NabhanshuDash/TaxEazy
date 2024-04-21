package com.example.taxeazy.models

import android.location.Location
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

data class UserData(
    var uid: String,
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
        "uid" to generateRandomId(),
        "name" to user.username,
        "email" to user.email,
        "password" to user.password,
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
    val key = SecretKeySpec("YourSecretKey12345".toByteArray(), "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val encryptedBytes = cipher.doFinal(password.toByteArray())
    return Base64.getEncoder().encodeToString(encryptedBytes)
}

fun decryptPassword(encryptedPassword: String): String {
    val key = SecretKeySpec("YourSecretKey12345".toByteArray(), "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, key)
    val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword))
    return String(decryptedBytes)
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


