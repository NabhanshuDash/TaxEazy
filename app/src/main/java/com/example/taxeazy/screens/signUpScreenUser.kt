package com.example.taxeazy.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taxeazy.MainActivity
import com.example.taxeazy.components.*
import com.example.taxeazy.models.UserData

@Composable
fun SignUpScreenUser() {
    var userData by remember { mutableStateOf(UserData("", "", "", "", "", "", "", emptyList(), emptyList(), emptyList(), emptyList())) }

    Surface {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize()
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
            CTextField(
                hint = "Username",
                value = userData.username,
                onValueChange = { newName -> userData = userData.copy(username = newName) },
                icon = Icons.Filled.Person
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Email",
                value = userData.email,
                onValueChange = { newEmail -> userData = userData.copy(email = newEmail) },
                icon = Icons.Filled.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Password",
                value = userData.password,
                onValueChange = { newPwd -> userData = userData.copy(password = newPwd) },
                icon = Icons.Filled.Lock
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Mobile No",
                value = userData.mobileNo,
                onValueChange = { newMobile -> userData = userData.copy(mobileNo = newMobile) },
                icon = Icons.Filled.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Aadhar No",
                value = userData.aadhaarNo,
                onValueChange = { newAadhar -> userData = userData.copy(aadhaarNo = newAadhar) },
                icon = Icons.Filled.CardMembership
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Business Name",
                value = userData.businessName,
                onValueChange = { newBname -> userData = userData.copy(businessName = newBname) },
                icon = Icons.Filled.Business
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Language",
                value = userData.language,
                onValueChange = { newLang -> userData = userData.copy(language = newLang) },
                icon = Icons.Filled.Language
            )
            Spacer(modifier = Modifier.height(16.dp))
            CButton(
                text = "Register",
                onClick = {
                    // Call registerUser function
                    val mainActivity = MainActivity()
                    mainActivity.printUserData(userData)
                }
            )
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignUpScreenUser()
}
