package com.example.taxeazy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RTIAct(
    val name: String,
    val information: String
)

@Composable
fun HomePage() {
    // Sample data for RTI acts
    val rtiActs = listOf(
        RTIAct(
            name = "RTI Act 2005",
            information = "The Right to Information Act, 2005 provides for setting out the practical regime of Right to Information for citizens."
        ),
        RTIAct(
            name = "RTI Amendment Act 2019",
            information = "The RTI Amendment Act, 2019 amended the Right to Information Act, 2005 to change the terms and conditions of service of the Chief Information Commissioner and Information Commissioners."
        ),
        RTIAct(
            name = "RTI Rules 2012",
            information = "The Right to Information Rules, 2012 lays down the procedure for seeking information under the Right to Information Act, 2005."
        ),
    )

    // State to track expanded status of each item
    val expandedStateList = remember { mutableStateListOf<Boolean>().apply { repeat(rtiActs.size) { add(false) } } }

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        item {
            Text(
                text = "RTI Acts",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        // Display RTI acts
        items(rtiActs) { act ->
            Column {
                Text(
                    text = act.name,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .clickable {
                            // Toggle expanded status when clicked
                            val index = rtiActs.indexOf(act)
                            expandedStateList[index] = !expandedStateList[index]
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                // Display additional information when expanded
                if (expandedStateList[rtiActs.indexOf(act)]) {
                    Text(
                        text = act.information,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}
