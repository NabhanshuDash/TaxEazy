package com.example.taxeazy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.ApplicationData
import com.example.taxeazy.models.ApplicationViewModel
import com.example.taxeazy.models.CaData
import com.example.taxeazy.models.UserViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun CaArea(cadata: CaData) {

    val viewModel : ApplicationViewModel = viewModel()
    val auth = FirebaseAuth.getInstance()
    val viewModel1 : UserViewModel = viewModel()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Username: ${cadata.username}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "UIN: ${cadata.uin}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email: ${cadata.email}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Status: ${if (cadata.status) "Active" else "Inactive"}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Mobile No: ${cadata.mobileNo}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Location: ${cadata.location.latitude}, ${cadata.location.longitude}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Language: ${cadata.language}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Current Applications: ${cadata.currentApplication.joinToString(", ")}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Notifications: ${cadata.notify.joinToString(", ")}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Reported: ${cadata.reported.joinToString(", ")}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            contactMyCA(cadata, viewModel, auth.currentUser, viewModel1)
        }) {
            Text(text = "Contact CA")
        }
        Text(
            text="If you click on Contact CA, either CA or You may call one another and It will lead to an application creation"
        )
    }
}

fun contactMyCA(caData: CaData, viewModel: ApplicationViewModel, currentUser: FirebaseUser?, viewModel1: UserViewModel) {

    viewModel.createApplication(ApplicationData(currentDocs = emptyList(), currentCA = caData.uid, status = false, record = "", payment = false, date = Timestamp.now(), uid = "", userId = currentUser?.uid.toString()), FirebaseFirestore.getInstance())
    val createdApplicationId = viewModel.getcreatedApplicationId()
    currentUser?.uid?.let {
        viewModel1.addApplicationIdToUser(createdApplicationId,
            it, FirebaseFirestore.getInstance())
    }

}