
package com.example.taxeazy.app


import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taxeazy.app.LandingPage
import com.example.taxeazy.components.CTextField
import com.example.taxeazy.components.GreyButtonComponent
import com.example.taxeazy.components.TextComponent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember

class LoginActivity : ComponentActivity() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var isLoading by remember { mutableStateOf(false) }
            var error by remember { mutableStateOf<String?>(null) }

            val coroutineScope = rememberCoroutineScope()

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
                        value = email,
                        onValueChange = { email = it },
                        hint = "Email",
                        icon = Icons.Filled.Person
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CTextField(
                        value = password,
                        onValueChange = {newPassword -> password = newPassword },
                        hint = "Password",
                        icon = Icons.Filled.Lock,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {Icon(Icons.Filled.Lock, contentDescription = "Password") }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    GreyButtonComponent(
                        value = if (isLoading) "Logging in..." else "Login",
                        colortxt = Color.White,
                        onClick = {
                            if (!isLoading) {
                                coroutineScope.launch {
                                    try {
                                        isLoading = true
                                        // Authenticate user
                                        auth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    // Login successful
                                                    // Navigate to the next screen or perform actions
                                                    val navigate = Intent(this@LoginActivity, LandingPage::class.java)
                                                    navigate.putExtra("UID", auth.currentUser?.uid)
                                                    startActivity(navigate)
                                                } else {
                                                    // Login failed
                                                    error = task.exception?.message ?: "An unknown error occurred"
                                                }
                                                isLoading = false
                                            }
                                    } catch (e: Exception) {
                                        // Handle exceptions
                                        error = e.message ?: "An unknown error occurred"
                                        isLoading = false
                                    }
                                }
                            }
                        }
                    )

                    error?.let {
                        TextComponent(
                            value = it,
                            fontStyletxt = FontStyle.Normal,
                            fweight = FontWeight.Normal,
                            size = 14,
                            minheight = 0,
                            colortxt = Color.Red,
                            alignment = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
    private fun navigateToLandingPage() {
        val navigate = Intent(this@LoginActivity, LandingPage::class.java)
        startActivity(navigate)
    }

    private fun showError(message: String) {
        // Show an error message to the user using a Toast
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }
}

