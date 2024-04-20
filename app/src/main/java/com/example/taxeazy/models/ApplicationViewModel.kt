package com.example.taxeazy.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class ApplicationViewModel : ViewModel() {
    // MutableState to hold the fetched application data
    var applicationDataList by mutableStateOf<List<ApplicationData>>(emptyList())

    fun fetchApplicationData(userData: UserData, db: FirebaseFirestore) {
        // Fetch application data using a coroutine scope
        viewModelScope.launch {
            applicationDataList = fetchApplications(userData, db)
        }
    }

    fun getApplicationData(): List<ApplicationData> {
        return applicationDataList
    }
    
    private suspend fun fetchApplications(userData: UserData, db: FirebaseFirestore): List<ApplicationData> {
        val applicationLists: MutableList<ApplicationData> = mutableListOf()

        // Iterate over the list of app IDs in userData
        for (appId in userData.applicationId) {
            // Fetch application data for each app ID and add it to the list
            val applicationData = fetchApplicationData(appId, db)
            if (applicationData != null) {
                applicationLists.add(applicationData)
            }
        }

        return applicationLists
    }

    private suspend fun fetchApplicationData(appId: String, db: FirebaseFirestore): ApplicationData? {
        try {
            val querySnapshot = db.collection("application")
                .whereEqualTo("uid", appId)
                .limit(1)
                .get()
                .await()

            for (document in querySnapshot.documents) {
                val caid = document.data?.get("caid").toString()
                val current = document.data?.get("current") as List<*>?
                val payment: Boolean = document.data?.get("payment") as Boolean
                val record = document.data?.get("record").toString()
                val uid = document.data?.get("uid").toString()
                val date = document.data?.get("date") as Timestamp
                val status = document.data?.get("status") as Boolean

                return ApplicationData(current, caid,  status,record, payment,date, uid)
            }

            return null
        } catch (e: Exception) {
            println("Error fetching application data: $e")
            return null
        }
    }
}