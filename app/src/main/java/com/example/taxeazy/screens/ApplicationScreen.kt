package com.example.taxeazy.screens

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.ApplicationViewModel
import com.example.taxeazy.models.UserData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

@Composable
fun ApplicationsScreen(userData: UserData, db: FirebaseFirestore) {
    // Obtain an instance of the ApplicationViewModel
    val viewModel: ApplicationViewModel = viewModel()

    // Trigger the data fetching operation when the component is first composed or when userData or db changes
    viewModel.fetchApplicationData(userData, db)

    val applicationDataList = viewModel.getApplicationData()

    // Add button in the bottom right corner
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        for(data in applicationDataList) {
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                    Text(text = data.currentCA)
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
            0.0, 0.0)), FirebaseFirestore.getInstance())
}

/**
 * Add button for search CAs
 * */
//FloatingActionButton(
//onClick = {
//    // Handle add button click action
//},
//modifier = Modifier
//.padding(16.dp),
//contentColor = MaterialTheme.colorScheme.onPrimary,
//elevation = FloatingActionButtonDefaults.elevation()
//) {
//    Icon(
//        imageVector = Icons.Filled.Add,
//        contentDescription = "Add"
//    )
//}