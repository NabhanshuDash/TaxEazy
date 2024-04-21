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

class CAViewModel : ViewModel() {
    // MutableState to hold the fetched CA data

    var cadata by mutableStateOf<CaData>(CaData("", "", "", "", "", false, "", GeoPoint(0.0, 0.0), "", emptyList(), emptyList(), emptyList(), emptyList()))
    var calist by mutableStateOf<List<CaData>>(emptyList())


    fun fetchCA(caid:String, db: FirebaseFirestore){
        viewModelScope.launch {
            cadata = fetchCAData(caid, db)
        }
    }

    fun fetchCAList(db : FirebaseFirestore) {
        viewModelScope.launch {
            calist = getAllCaData(db)
        }
    }

    fun getcadata() : CaData {
        return cadata
    }

    fun getcalist() : List<CaData> {
        return calist
    }

    suspend fun fetchCAData(caid: String, db: FirebaseFirestore): CaData {
        try {
            val querySnapshot = db.collection("ca")
                .whereEqualTo("uid", caid)
                .limit(1)
                .get()
                .await()

            for (document in querySnapshot.documents) {
                val uid = document.data?.get("caid") as String
                val name = document.data?.get("name") as String
                val uin = document.data?.get("uin") as String
                val email = document.data?.get("email") as String
                val password = document.data?.get("password") as String
                val status = document.data?.get("status") as Boolean
                val mobileNo = document.data?.get("mobile") as String
                val location = document.data?.get("location") as GeoPoint
                val language = document.getString("language") ?: ""
                val currentApplication = document.get("aid") as List<String>
                val notify = document.get("notification") as List<String>
                val reported = document.get("reported") as List<String>
                val clients = document.get("clients") as List<String>

                return CaData(uid, name, uin, email, password, status, mobileNo, location, language, currentApplication, notify, reported, clients)
            }

            return cadata
        } catch (e: Exception) {
            println("Error fetching CA data: $e")
            return cadata
        }
    }

    suspend fun getAllCaData(db: FirebaseFirestore): List<CaData> {
        val querySnapshot = db.collection("ca")
            .get()
            .await()

        val caDataList = mutableListOf<CaData>()

        for (document in querySnapshot.documents) {
            val uid = document.data?.get("caid") as? String ?: ""
            val username = document.data?.get("username") as? String ?: ""
            val uin = document.data?.get("uin") as? String ?: ""
            val email = document.data?.get("email") as? String ?: ""
            val password = document.data?.get("password") as? String ?: ""
            val status = document.data?.get("status") as? Boolean ?: false
            val mobileNo = document.data?.get("mobileNo") as? String ?: ""
            val location = document.data?.get("location") as GeoPoint
            val language = document.data?.get("language") as? String ?: ""
            val currentApplication = (document.data?.get("currentApplication") as? List<String>) ?: emptyList()
            val notify = (document.data?.get("notify") as? List<String>) ?: emptyList()
            val reported = (document.data?.get("reported") as? List<String>) ?: emptyList()
            val clients = (document.data?.get("clients") as? List<String>) ?: emptyList()


            val caData = CaData(uid, username, uin, email, password, status, mobileNo, location, language, currentApplication, notify, reported, clients)
            caDataList.add(caData)
        }

        return caDataList
    }

    fun addApplicationIdToCurrentCA(appId: String, db: FirebaseFirestore, uid: String) {
        viewModelScope.launch {
            try {
                db.collection("ca").document(uid)
                    .update("aid", FieldValue.arrayUnion(appId))
                    .await()
                println("Application ID added to the current CA successfully.")
            } catch (e: Exception) {
                println("Error adding Application ID to the current CA: $e")
            }
        }
    }

}