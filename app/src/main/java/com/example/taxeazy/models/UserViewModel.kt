package com.example.taxeazy.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewModel : ViewModel() {

    var currentUser by mutableStateOf<UserData>(UserData(
        "",
        username = "",
        email = "",
        password = "",
        mobileNo = "",
        aadhaarNo = "",
        businessName = "",
        language = "",
        applicationId = listOf(),
        personalStore = listOf(),
        notify = listOf(),
        reported = listOf(),
        location = GeoPoint(0.0, 0.0) // Assuming initial location is (0.0, 0.0)
    ))



    fun generateFetchUser(uid : String, db: FirebaseFirestore) {
        viewModelScope.launch {
            currentUser = fetchUser(uid,db) ?: UserData(
                uid = "",
                username = "",
                email = "",
                password = "",
                mobileNo = "",
                aadhaarNo = "",
                businessName = "",
                language = "",
                applicationId = listOf(),
                personalStore = listOf(),
                notify = listOf(),
                reported = listOf(),
                location = GeoPoint(0.0, 0.0));
        }
    }

    fun searchUser() : UserData{
        return currentUser
    }

    suspend fun fetchUser(uid: String, db: FirebaseFirestore): UserData? {
        try {
            val querySnapshot = db.collection("users")
                .whereEqualTo("userId", uid)
                .limit(1)
                .get()
                .await()

            for (document in querySnapshot.documents) {
                val uid = document.data?.get("uid").toString()
                val username = document.getString("name") ?: ""
                val email = document.getString("email") ?: ""
                val password = document.getString("password") ?: ""
                val mobileNo = document.getString("mobile") ?: ""
                val aadhaarNo = document.getString("aadhaar") ?: ""
                val businessName = document.getString("businessname") ?: ""
                val language = document.getString("language") ?: ""
                // Assuming getCurrentLocation() returns a Location object
                val location = document.getGeoPoint("location") ?: GeoPoint(0.0, 0.0)
                val applicationId = document.get("appid") as? List<String> ?: emptyList()
                val notify = document.get("notid") as? List<String> ?: emptyList()
                val reported = document.get("report") as? List<String> ?: emptyList()
                val personalStore = document.get("storage") as? List<String> ?: emptyList()

                return UserData(uid, username, email, password, mobileNo, aadhaarNo, businessName, language, applicationId, notify, reported, personalStore, location)
            }

            return null
        } catch (e: Exception) {
            println("Error fetching user data: $e")
            return null
        }
    }

    fun addApplicationIdToUser(appId:String, uid: String, db : FirebaseFirestore) {
        viewModelScope.launch {
            try {
                db.collection("users").document(uid)
                    .update("applicationId", FieldValue.arrayUnion(appId))
                    .await()
                println("Application ID added to the current CA successfully.")
            } catch (e: Exception) {
                println("Error adding Application ID to the current CA: $e")
            }
        }
    }


}