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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.CAViewModel
import com.example.taxeazy.models.CaData
import com.google.firebase.firestore.FirebaseFirestore
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
fun CaSelectionScreen() {

    var db = FirebaseFirestore.getInstance()

    var viewModel : CAViewModel = viewModel()
    viewModel.fetchCAList(db)
    var caDataList = viewModel.getcalist()

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

    CaSelectionScreen()

}