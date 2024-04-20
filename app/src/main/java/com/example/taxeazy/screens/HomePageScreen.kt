package com.example.taxeazy.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier


@Composable
fun HomePage() {
    // Sample data for RTI acts
    val rtiActs = listOf(
        "RTI Act 2005",
        "RTI Amendment Act 2019",
        "RTI Rules 2012"
    )

    // State to track expanded status of each item
    val expandedStateList = remember { mutableStateListOf<Boolean>().apply { repeat(rtiActs.size) { add(false) } } }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(
                text = "RTI Acts",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Display RTI acts
        items(rtiActs) { act ->
            Text(
                text = act,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .clickable {
                        // Toggle expanded status when clicked
                        val index = rtiActs.indexOf(act)
                        expandedStateList[index] = !expandedStateList[index]
                    }
            )
            // Display additional information when expanded
            if (expandedStateList[rtiActs.indexOf(act)]) {
                Text(
                    text = "Additional information about $act",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
