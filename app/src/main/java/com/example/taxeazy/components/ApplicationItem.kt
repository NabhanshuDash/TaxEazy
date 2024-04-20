package com.example.taxeazy.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taxeazy.models.ApplicationData
import com.example.taxeazy.models.CaData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(date)
}

@Composable
fun ApplicationItem(applicationData: ApplicationData, cadata: CaData) {
    val elevation = 4.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val formattedDate = formatDate(applicationData.date.toDate())
            Text(
                text = "Application Date: ${formattedDate}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "CA's Name: ${cadata.username}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Status: ${applicationData.status}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview
@Composable
fun ApplicationItemPreview(){

    val applicationData = ApplicationData(
        currentDocs = listOf("/collection/docs"),
        currentCA = "456798",
        payment = true,
        record = "/collection/docs",
        status = true,
        uid = "789456",
        date = Timestamp.now()
    )

    val caData = CaData(
        username = "John Doe",
        uin = "1234567890",
        email = "john.doe@example.com",
        password = "password123",
        status = true,
        mobileNo = "1234567890",
        location = GeoPoint(37.7749, -122.4194), // Example coordinates for San Francisco
        language = "English",
        currentApplication = listOf("application1", "application2"),
        notify = listOf("notification1", "notification2"),
        reported = listOf("report1", "report2")
    )

    ApplicationItem(applicationData, caData)
}