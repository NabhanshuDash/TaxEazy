package com.example.taxeazy.screens

import android.net.Uri
import androidx.compose.material3.Surface
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner

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

//@Composable
//fun ApplicationScreen() {
//
//    Surface{
//
//    }
//
//}