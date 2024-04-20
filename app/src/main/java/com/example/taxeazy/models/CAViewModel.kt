package com.example.taxeazy.models

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.tasks.await

class CAViewModel : ViewModel() {
    // MutableState to hold the fetched CA data
    suspend fun fetchCAData(caid: String, db: FirebaseFirestore): CaData? {
        try {
            val querySnapshot = db.collection("ca")
                .whereEqualTo("uid", caid)
                .limit(1)
                .get()
                .await()

            for (document in querySnapshot.documents) {
                val name = document.getString("name") ?: ""
                val uin = document.getString("uin") ?: ""
                val email = document.getString("email") ?: ""
                val password = document.getString("password") ?: ""
                val status = document.getBoolean("status") ?: false
                val mobileNo = document.getString("mobile") ?: ""
                val location = document.getGeoPoint("location") ?: GeoPoint(0.0, 0.0)
                val language = document.getString("language") ?: ""
                val currentApplication = document.get("aid") as List<String>
                val notify = document.get("notification") as List<String>
                val reported = document.get("reported") as List<String>

                return CaData(name, uin, email, password, status, mobileNo, location, language, currentApplication, notify, reported)
            }

            return null
        } catch (e: Exception) {
            println("Error fetching CA data: $e")
            return null
        }
    }
}