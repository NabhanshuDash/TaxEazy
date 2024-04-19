package com.example.taxeazy.models

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.tasks.await
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class CaData (
    val username: String,
    val uin: String,
    val email: String,
    val password: String,
    val status: Boolean,
    val mobileNo: String,
    val location: GeoPoint,
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

//Search CA Closest to you

suspend fun getCAList(db: FirebaseFirestore): List<CaData> {

    val querySnapshot = db.collection("ca")
        .get()
        .await()

    val caList = mutableListOf<CaData>()

    for (document in querySnapshot.documents) {
        val caid = document["caid"] as String
        val name = document["name"] as String
        val location = document["location"] as GeoPoint
        val language = document["language"] as String
        val status = document["status"] as Boolean

        val ca = CaData(caid, name,"","",status,"", location,language, emptyList(), emptyList(),
            emptyList())
        caList.add(ca)
    }

    return caList
}

fun calculateDistance(
    userLat: Double,
    userLng: Double,
    caLat: Double,
    caLng: Double
): Double {
    val earthRadius = 6371 // Radius of the Earth in kilometers

    val dLat = Math.toRadians(caLat - userLat)
    val dLng = Math.toRadians(caLng - userLng)

    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(userLat)) * cos(Math.toRadians(caLat)) *
            sin(dLng / 2) * sin(dLng / 2)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadius * c
}

suspend fun findClosestCA(user: UserData, db: FirebaseFirestore): CaData? {
    val caList = getCAList(db)

    var minDistance = Double.MAX_VALUE
    var closestCA: CaData? = null

    for (ca in caList) {
        val distance = calculateDistance(
            user.location.latitude,
            user.location.longitude,
            ca.location.latitude,
            ca.location.longitude
        )
        if (distance < minDistance) {
            minDistance = distance
            closestCA = ca
        }
    }

    return closestCA
}

