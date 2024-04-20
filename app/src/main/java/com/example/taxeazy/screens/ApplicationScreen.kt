package com.example.taxeazy.screens

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.ApplicationData
import com.example.taxeazy.models.ApplicationViewModel
import com.example.taxeazy.models.UserData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ApplicationScreen(userData: UserData, db: FirebaseFirestore) {
    // Obtain an instance of the ApplicationViewModel
    val viewModel: ApplicationViewModel = viewModel()

    // Trigger the data fetching operation when the component is first composed or when userData or db changes
    viewModel.fetchApplicationData(userData, db)

    // Observe the application data list from the ViewModel
    val applicationDataList by viewModel.applicationDataList.collectAsState(initial = emptyList())
}



@Composable
fun ApplicationItem() {
    
    Surface {
        Text(text = "Application")
    }
    
}
