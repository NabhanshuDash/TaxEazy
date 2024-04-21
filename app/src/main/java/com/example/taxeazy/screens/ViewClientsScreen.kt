package com.example.taxeazy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.ApplicationData
import com.example.taxeazy.models.ApplicationViewModel
import com.example.taxeazy.models.CaData
import com.example.taxeazy.models.UserData
import com.example.taxeazy.models.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

@Composable
fun ViewClientsScreen(userData: UserData, applicationData: ApplicationData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Client Name: ${userData.username}",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Status: ${applicationData.status}",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Bussiness Name: ${userData.businessName}",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Languages Spoken: ${userData.language}",
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun ClientViewScreen(caData: CaData) {

    var db = FirebaseFirestore.getInstance()

    var viewModel : UserViewModel = viewModel()
    var viewModel1 : ApplicationViewModel = viewModel()

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(caData.clients.size) { index ->
            viewModel.generateFetchUser(caData.clients.get(index), db)
            viewModel1.fetchSingleApplicationData(caData.currentApplication.get(index), db)
            ViewClientsScreen(viewModel.searchUser(), viewModel1.getsingleApplicationData())
        }
    }
}

@Preview
@Composable
fun ViewClientsPreview(){

    ClientViewScreen(CaData("","", "", "", "", false, "", GeoPoint(0.0, 0.0), "", emptyList(), emptyList(), emptyList(), emptyList()))

}