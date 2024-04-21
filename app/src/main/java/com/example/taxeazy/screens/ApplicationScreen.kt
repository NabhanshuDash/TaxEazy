package com.example.taxeazy.screens

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
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
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\uD83C\uDF3F  Your Applications",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        items(applicationDataList.size) { k ->
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                ApplicationItem(applicationData = applicationDataList[k], cadata = caDataList[k])
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
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(applicationDataList.size) {k ->
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                ApplicationItem(applicationData = applicationDataList[k], userData = userDataList[k])
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
        UserData("","", "", "", "", "", "", "", mutableStringList, listOf("9f2637c9-2f8a-4b07-bbb7-1442983fa277", 	"835197c1-de21-4b1f-abf8-f6f7ae41a0f1"), emptyList(), emptyList(), GeoPoint(
            0.0, 0.0)))
}