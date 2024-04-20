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
import com.example.taxeazy.app.MainActivity
import com.example.taxeazy.app.SignUpCa
import com.example.taxeazy.app.SignUpUser
import com.example.taxeazy.components.*
import com.example.taxeazy.models.CaData
import com.google.firebase.firestore.GeoPoint

@Composable
fun SignUpScreenCA(){
    var caData by remember { mutableStateOf(CaData("","","","",true,"",GeoPoint(0.0, 0.0),"",
        emptyList(), emptyList(), emptyList())) }
    Surface {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center
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
                value = caData.username,
                onValueChange = { newName -> caData = caData.copy(username = newName) },
                icon = Icons.Filled.Person
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Membership Id",
                value = caData.uin,
                onValueChange = { newMembershipId -> caData = caData.copy(uin = newMembershipId) },
                icon = Icons.Filled.CardMembership
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Email",
                value = caData.email,
                onValueChange = { newEmail -> caData = caData.copy(email = newEmail) },
                icon = Icons.Filled.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Password",
                value = caData.password,
                onValueChange = { newPwd -> caData = caData.copy(password = newPwd) },
                icon = Icons.Filled.Lock
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Mobile No",
                value = caData.mobileNo,
                onValueChange = { newMobile -> caData = caData.copy(mobileNo = newMobile) },
                icon = Icons.Filled.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))
            CTextField(
                hint = "Language",
                value = caData.language,
                onValueChange = { newLang -> caData = caData.copy(language = newLang) },
                icon = Icons.Filled.Language
            )
            Spacer(modifier = Modifier.height(16.dp))
            CButton(
                text = "Register",
                onClick = {
                    // Call registerUser function
                    val signUpCa = SignUpCa()
                    signUpCa.signUpCa(caData)
                }
            )
        }
    }
}

@Preview
@Composable
fun SignUpScreenCAPreview() {
    SignUpScreenCA()
}