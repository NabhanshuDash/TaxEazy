package com.example.taxeazy.screens

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.taxeazy.models.CaData

@Composable
fun signUpScreenCA(){
    Surface {
        var caData by remember { mutableStateOf(CaData ("","","",false,"",0.0,"",""))

    }
}