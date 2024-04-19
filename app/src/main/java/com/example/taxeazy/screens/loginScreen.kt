package com.example.taxeazy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taxeazy.components.*
import com.example.taxeazy.models.UserData

@Composable
fun LoginScreen() {
    var userData by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextComponent(
                value = "Sign Up",
                fontStyletxt = FontStyle.Normal,
                fweight = FontWeight.Bold,
                size = 24,
                minheight = 0,
                colortxt = Color.Black,
                alignment = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            NormalTextFieldComponent(
                LabelValue = "Username",
                img = Icons.Filled.Person,

            )

            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextFieldComponent(
                labelValue = "Password",
                img = Icons.Filled.Lock,

            )

            Spacer(modifier = Modifier.height(16.dp))
            ButtonComponent(
                value = "Login",
                colortxt = Color.White
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
