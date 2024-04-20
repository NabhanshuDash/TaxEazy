package com.example.taxeazy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taxeazy.models.CaData
import com.google.firebase.firestore.GeoPoint

@Composable
fun CaChoose(caData: CaData) {
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
                text = "Name: ${caData.username}",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Languages: ${caData.language}",
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun CaSelectionScreen(caDataList: List<CaData>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(caDataList.size) { index ->
            CaChoose(caDataList[index])
        }
    }
}

@Preview
@Composable
fun CaChoosePreview(){

    val caDataList = listOf(
        CaData(
            username = "John Doe",
            language = "English, Spanish",
            password = "",
            uin = "",
            email = "",
            status = false,
            mobileNo = "",
            location = GeoPoint(37.7749, -122.4194),
            currentApplication = emptyList(),
            notify = emptyList(),
            reported = emptyList()
        ),
        CaData(
            username = "Alice Smith",
            language = "French, German",
            password = "",
            uin = "",
            email = "",
            status = false,
            mobileNo = "",
            location = GeoPoint(37.7749, -122.4194),
            currentApplication = emptyList(),
            notify = emptyList(),
            reported = emptyList()
        ),
        CaData(
            username = "Bob Johnson",
            language = "Japanese, Korean",
            password = "",
            uin = "",
            email = "",
            status = false,
            mobileNo = "",
            location = GeoPoint(37.7749, -122.4194),
            currentApplication = emptyList(),
            notify = emptyList(),
            reported = emptyList()
        )
        // Add more CAData objects as needed
    )

    CaSelectionScreen(caDataList)

}