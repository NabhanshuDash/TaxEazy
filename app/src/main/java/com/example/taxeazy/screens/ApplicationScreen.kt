package com.example.taxeazy.screens

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.components.ApplicationItem
import com.example.taxeazy.models.ApplicationViewModel
import com.example.taxeazy.models.CAViewModel
import com.example.taxeazy.models.CaData
import com.example.taxeazy.models.UserData
import com.example.taxeazy.models.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

@Composable
fun ApplicationsScreen(userData: UserData) {

    val db = FirebaseFirestore.getInstance()

    // Obtain an instance of the ApplicationViewModel
    val viewModel: ApplicationViewModel = viewModel()
    val viewModelCa: CAViewModel = viewModel()

    // Trigger the data fetching operation when the component is first composed or when userData or db changes
    viewModel.fetchApplicationData(userData, db)

    val applicationDataList = viewModel.getApplicationData()

    val caDataList = mutableListOf<CaData>()
    for(doc in applicationDataList) {
        viewModelCa.fetchCA(doc.currentCA, db)
        caDataList.add(viewModelCa.getcadata())
    }

    // Add button in the bottom right corner
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        for((k, data) in applicationDataList.withIndex()) {
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                    ApplicationItem(applicationData = data, cadata = caDataList[k])
            }
        }
    }
}


@Composable
fun ApplicationsScreen(caData: CaData) {

    val db = FirebaseFirestore.getInstance()

    // Obtain an instance of the ApplicationViewModel
    val viewModel: ApplicationViewModel = viewModel()
    val viewModelUser: UserViewModel = viewModel()

    // Trigger the data fetching operation when the component is first composed or when userData or db changes
    viewModel.fetchApplicationDataCA(caData, db)

    val applicationDataList = viewModel.getapplicationDataCA()

    val userDataList = mutableListOf<UserData>()
    for(doc in applicationDataList) {
        viewModelUser.generateFetchUser(doc.userId, db)
        userDataList.add(viewModelUser.searchUser())
    }

    // Add button in the bottom right corner
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        for((k, data) in applicationDataList.withIndex()) {
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                ApplicationItem(applicationData = data, userData = userDataList[k])
            }
        }
    }
}

@Preview
@Composable
fun ApplicationScreenPreview()
{
    val mutableStringList = mutableListOf("789456")
    ApplicationsScreen(
        UserData("", "", "", "", "", "", "", mutableStringList, emptyList(), emptyList(), emptyList(), GeoPoint(
            0.0, 0.0)))
}