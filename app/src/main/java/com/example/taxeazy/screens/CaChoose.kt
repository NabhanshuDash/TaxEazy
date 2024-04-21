package com.example.taxeazy.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taxeazy.models.CAViewModel
import com.example.taxeazy.models.CaData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

@Composable
fun CaChoose(
    caData: CaData,
    setSelectedCA: (CaData) -> Unit,
    setIsCaDetailsSelected: (Boolean) -> Unit, ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { setSelectedCA(caData); setIsCaDetailsSelected(true) },
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

    val db = FirebaseFirestore.getInstance()

    val viewModel : CAViewModel = viewModel()
    viewModel.fetchCAList(db)
    val caDataList = viewModel.getcalist()

    var (isCaDetailsSelected, setIsCaDetailsSelected) = remember {
        mutableStateOf(false)
    }
    var (selectedCA, setSelectedCA) = remember {
        mutableStateOf(CaData("","", "", "", "", false, "", GeoPoint(0.0, 0.0), "", emptyList(), emptyList(), emptyList()))
    }

    if(isCaDetailsSelected) {
        CaArea(selectedCA)
    }
    else {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(caDataList.size) { index ->
                CaChoose(caDataList[index], setSelectedCA, setIsCaDetailsSelected)
            }
        }
    }
}

@Preview
@Composable
fun CaChoosePreview(){

    CaSelectionScreen()

}