package com.example.taxeazy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.taxeazy.components.*
import com.example.taxeazy.models.UserData

@Composable
fun SignUpScreenUser() {
    var userData by remember { mutableStateOf(UserData("", "", "", "", "", "", "", emptyArray(), emptyArray(), emptyArray(), emptyArray())) }

    Surface {
        Column(
            modifier = Modifier.padding(16.dp)
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
                value = username,
                onValueChange = { username = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailTextFieldComponent(
                LabelValue = "Email",
                img = Icons.Filled.Email,
                value = email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextFieldComponent(
                labelValue = "Password",
                img = Icons.Filled.Lock,
                value = password,
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            NormalTextFieldComponent(
                LabelValue = "Mobile No",
                img = Icons.Filled.Phone,
                value = mobileNo,
                onValueChange = { mobileNo = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            NormalTextFieldComponent(
                LabelValue = "Aadhar No",
                img = Icons.Filled.CardMembership,
                value = aadharNo,
                onValueChange = { aadharNo = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            NormalTextFieldComponent(
                LabelValue = "Business Name",
                img = Icons.Filled.Business,
                value = businessName,
                onValueChange = { businessName = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            NormalTextFieldComponent(
                LabelValue = "Language",
                img = Icons.Filled.Language,
                value = language,
                onValueChange = { language = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CheckboxComponent(
                value = "Agree to Terms and Conditions",
                size = 16
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonComponent(
                value = "Sign Up",
                colortxt = Color.White
            )
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignUpScreenUser()
}
