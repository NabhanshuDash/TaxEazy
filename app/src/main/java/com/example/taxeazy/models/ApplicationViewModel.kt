package com.example.taxeazy.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class ApplicationViewModel : ViewModel() {
    // MutableState to hold the fetched application data
    var applicationDataList by mutableStateOf<List<List<ApplicationData>>>(emptyList())

    fun fetchApplicationData(userData: UserData, db: FirebaseFirestore) {
        // Fetch application data using a coroutine scope
        viewModelScope.launch {
            applicationDataList = fetchApplications(userData, db)
        }
    }

    fun getApplicationDataList(): List<List<ApplicationData>> {
        return applicationDataList
    }

    private suspend fun fetchApplications(userData: UserData, db: FirebaseFirestore): List<List<ApplicationData>> {
        val applicationLists: MutableList<List<ApplicationData>> = mutableListOf()

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

    private suspend fun fetchApplicationData(appId: String, db: FirebaseFirestore): List<ApplicationData>? {
        try {
            val querySnapshot = db.collection("application")
                .whereEqualTo("uid", appId)
                .limit(1)
                .get()
                .await()

            val applicationDataList = mutableListOf<ApplicationData>()

            for (document in querySnapshot.documents) {
                val caid = document.getString("caid")
                val current = document.get("current") as List<*>?
                val payment = document.getBoolean("payment") ?: false
                val record = document.getBoolean("record") ?: false
                val uid = document.getString("uid")

                val applicationData = caid?.let { ApplicationData(current, it, payment, record, uid) }
                if (applicationData != null) {
                    applicationDataList.add(applicationData)
                }
            }

            return applicationDataList
        } catch (e: Exception) {
            println("Error fetching application data: $e")
            return null
        }
    }
}