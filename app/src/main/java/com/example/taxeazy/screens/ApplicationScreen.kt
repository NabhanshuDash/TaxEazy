package com.example.taxeazy.screens

import androidx.compose.runtime.*

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import androidx.compose.material3.FloatingActionButtonDefaults

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.ApplicationData
import com.example.taxeazy.models.ApplicationViewModel
import com.example.taxeazy.models.UserData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint


//@Composable
//fun FileSelectionPopup(onFileSelected: (Uri) -> Unit) {
//    var showDialog by remember { mutableStateOf(false) }
//
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    val getContent = rememberLauncher { content ->
//        // Launch file picker activity
//        launchGetContent(content) { uri ->
//            onFileSelected(uri)
//            showDialog = false // Close the dialog after file selection
//        }
//    }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            title = { Text(text = stringResource(id = R.string.select_file)) },
//            confirmButton = {
//                Button(onClick = { getContent.launch("image/*") }) {
//                    Text(text = stringResource(id = R.string.select))
//                }
//            },
//            dismissButton = {
//                Button(onClick = { showDialog = false }) {
//                    Text(text = stringResource(id = R.string.cancel))
//                }
//            },
//            text = { Text(text = stringResource(id = R.string.select_file_prompt)) }
//        )
//    }
//}
//
//@Composable
//fun rememberLauncher(
//    callback: (content: String) -> Unit
//): ActivityResultLauncher<String> {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    return remember(context, lifecycleOwner) {
//        context.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//            callback(uri.toString())
//        }
//    }
//}
//
//@Preview
//@Composable
//fun FileSelectionPopupPreview() {
//    Column {
//        // Add other composables as needed
//        FileSelectionPopup(onFileSelected = { /* Handle selected file */ })
//    }
//}

@Composable
fun ApplicationItem(applicationData: ApplicationData) {
    // Composable for displaying a single application's layout
    // Customize this based on your application data structure
    // For example:
    // Text(applicationData.caid)
}

@Composable
fun ApplicationsScreen(userData: UserData, db: FirebaseFirestore) {
    // Obtain an instance of the ApplicationViewModel
    val viewModel: ApplicationViewModel = viewModel()

    // Trigger the data fetching operation when the component is first composed or when userData or db changes
    viewModel.fetchApplicationData(userData, db)

    val applicationData = viewModel.getApplicationDataList()

    // Display the list of applications
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(applicationData.size) { index ->
            val applicationList = applicationData[index]
            // Display each application using ApplicationItem composable
            ApplicationItem(applicationData[0][0])
        }
    }

    // Add button in the bottom right corner
    FloatingActionButton(
        onClick = {
            // Handle add button click action
            // For example, navigate to the add application screen
        },
        modifier = Modifier
            .padding(16.dp),
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = FloatingActionButtonDefaults.elevation()
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add"
        )
    }
}


@Preview
@Composable
fun AppicationScreenPreview()
{
    ApplicationsScreen(
        UserData("", "", "", "", "", "", "", emptyList(), emptyList(), emptyList(), emptyList(), GeoPoint(
            0.0, 0.0)), FirebaseFirestore.getInstance())
}