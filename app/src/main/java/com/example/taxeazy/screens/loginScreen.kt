package com.example.taxeazy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taxeazy.components.CTextField
import com.example.taxeazy.components.GreyButtonComponent
import com.example.taxeazy.components.TextComponent

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextComponent(
                value = "Login",
                fontStyletxt = FontStyle.Normal,
                fweight = FontWeight.Bold,
                size = 24,
                minheight = 0,
                colortxt = Color.Black,
                alignment = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            CTextField(
                value = username,
                onValueChange = { username = it },
                hint = "Username",
                icon = Icons.Filled.Person
            )

            Spacer(modifier = Modifier.height(16.dp))

            CTextField(
                value = password,
                onValueChange = { password = it },
                hint = "Password",
                icon = Icons.Filled.Lock,

                )

            Spacer(modifier = Modifier.height(20.dp))

            GreyButtonComponent(
                value = "Login",
                colortxt = Color.White,
                onClick = { /* Handle login click */ }
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}