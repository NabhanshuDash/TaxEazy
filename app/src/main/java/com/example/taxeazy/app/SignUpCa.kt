package com.example.taxeazy.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taxeazy.components.CButton
import com.example.taxeazy.components.CTextField
import com.example.taxeazy.components.TextComponent
import com.example.taxeazy.models.CaData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class SignUpCa: ComponentActivity() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var caData by remember {
                mutableStateOf(
                    CaData("",
                        "", "", "", "", true, "", GeoPoint(0.0, 0.0), "",
                        emptyList(), emptyList(), emptyList()
                    )
                )
            }
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
                        icon = Icons.Filled.Person,
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CTextField(
                        hint = "Membership Id",
                        value = caData.uin,
                        onValueChange = { newMembershipId ->
                            caData = caData.copy(uin = newMembershipId)
                        },
                        icon = Icons.Filled.CardMembership,
                        leadingIcon = { Icon(Icons.Filled.CardMembership, contentDescription = "Membership Id") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CTextField(
                        hint = "Email",
                        value = caData.email,
                        onValueChange = { newEmail -> caData = caData.copy(email = newEmail) },
                        icon = Icons.Filled.Email,
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CTextField(
                        hint = "Password",
                        value = caData.password,
                        onValueChange = { newPwd -> caData = caData.copy(password = newPwd) },
                        icon = Icons.Filled.Lock,
                                visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {Icon(Icons.Filled.Lock, contentDescription = "Password") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CTextField(
                        hint = "Mobile No",
                        value = caData.mobileNo,
                        onValueChange = { newMobile -> caData = caData.copy(mobileNo = newMobile) },
                        icon = Icons.Filled.Phone,
                        leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = "Mobile No") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CTextField(
                        hint = "Language",
                        value = caData.language,
                        onValueChange = { newLang -> caData = caData.copy(language = newLang) },
                        icon = Icons.Filled.Language,
                        leadingIcon = { Icon(Icons.Filled.Language, contentDescription = "Language") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CButton(
                        text = "Register",
                        onClick = {
                            // Call registerUser function
                            val signUpCa = SignUpCa()
                            signUpCa.signUpCa(caData)
                            val navigate = Intent(this@SignUpCa, LandingPageUser::class.java)
                            startActivity(navigate)
                        }
                    )
                }
            }
        }
    }
    private fun signUpCa(caData: CaData) {
        // Validate CA data before proceeding
        if (!isValidCaData(caData)) {
            println("Invalid CA data provided")
            return
        }

        auth.createUserWithEmailAndPassword(caData.email, caData.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    val userId = firebaseUser?.uid

                    if (userId != null) {
                        firestore.collection("ca").document(userId)
                            .set(caData)
                            .addOnSuccessListener {
                                println("Data saved successfully")
                                navigateToLandingPage()
                            }
                            .addOnFailureListener { e ->
                                println("Error saving Data: $e")
                                // Show an error message to the user
                                showError("Failed to save CA data. Please try again.")
                            }
                    }
                } else {
                    println("Error creating user: ${task.exception}")
                    // Show an error message to the user
                    showError("Failed to create CA user. Please try again.")
                }
            }
            .addOnFailureListener { e ->
                println("General error: $e")
                // Show an error message to the user
                showError("An unknown error occurred. Please try again.")
            }
    }


    private fun isValidCaData(caData: CaData): Boolean {
        // Implement your validation logic here
        // For example, check if email is valid, password meets criteria, etc.
        return true
    }

    private fun navigateToLandingPage() {
        val navigate = Intent(this@SignUpCa, LandingPageUser::class.java)
        startActivity(navigate)
    }

    private fun showError(message: String) {
        // You can show this error message to the user using a Toast, Snackbar, or any other UI component
        Toast.makeText(this@SignUpCa, message, Toast.LENGTH_SHORT).show()
    }
}