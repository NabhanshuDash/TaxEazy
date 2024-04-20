package com.example.taxeazy.app


import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.taxeazy.Utils.CryptoUtils
import com.example.taxeazy.app.LandingPage
import com.example.taxeazy.components.CTextField
import com.example.taxeazy.components.GreyButtonComponent
import com.example.taxeazy.components.TextComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginActivity : ComponentActivity() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    var error by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var isLoading by remember { mutableStateOf(false) }

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
                        onValueChange = { password = it },
                        hint = "Password",
                        icon = Icons.Filled.Lock
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
                                        loginUser(email, password)
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

    private fun decryptPassword(encryptedPassword: String): String {
        val salt = "YourSaltHere" // You need to retrieve the salt from the database or another secure source
        return CryptoUtils.decryptPassword(encryptedPassword, salt)
    }

    private fun loginUser(email: String, password: String) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            try {
                val query = db.collection("users")
                    .whereEqualTo("email", email)
                    .limit(1)
                    .get()
                    .await()

                if (!query.isEmpty) {
                    val userDoc = query.documents[0]
                    val storedPassword = userDoc.getString("password") ?: ""

                    val decryptedPassword = decryptPassword(storedPassword)

                    // Authenticate user
                    auth.signInWithEmailAndPassword(email, decryptedPassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Login successful
                                // Navigate to the next screen or perform actions
                                val navigate = Intent(this@LoginActivity, LandingPage::class.java)
                                startActivity(navigate)
                            } else {
                                // Login failed
                                error = task.exception?.message ?: "An unknown error occurred"
                            }
                            isLoading = false
                        }
                } else {
                    error = "User not found"
                }
            } catch (e: Exception) {
                error = e.message ?: "An unknown error occurred"
                isLoading = false
            }
        }
    }
}

//package com.example.taxeazy.app
//
//
//import android.content.Intent
//import android.os.Bundle
//import android.provider.ContactsContract.CommonDataKinds.Email
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.taxeazy.app.LandingPage
//import com.example.taxeazy.components.CTextField
//import com.example.taxeazy.components.GreyButtonComponent
//import com.example.taxeazy.components.TextComponent
//import com.google.firebase.auth.FirebaseAuth
//import kotlinx.coroutines.launch
//
//class LoginActivity : ComponentActivity() {
//    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            var email by remember { mutableStateOf("") }
//            var password by remember { mutableStateOf("") }
//            var isLoading by remember { mutableStateOf(false) }
//            var error by remember { mutableStateOf<String?>(null) }
//
//            val coroutineScope = rememberCoroutineScope()
//
//            Surface(
//                color = Color.White,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    TextComponent(
//                        value = "Login",
//                        fontStyletxt = FontStyle.Normal,
//                        fweight = FontWeight.Bold,
//                        size = 24,
//                        minheight = 0,
//                        colortxt = Color.Black,
//                        alignment = TextAlign.Center
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    CTextField(
//                        value = email,
//                        onValueChange = { email = it },
//                        hint = "Email",
//                        icon = Icons.Filled.Person
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    CTextField(
//                        value = password,
//                        onValueChange = { password = it },
//                        hint = "Password",
//                        icon = Icons.Filled.Lock
//                    )
//
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    GreyButtonComponent(
//                        value = if (isLoading) "Logging in..." else "Login",
//                        colortxt = Color.White,
//                        onClick = {
//                            if (!isLoading) {
//                                coroutineScope.launch {
//                                    try {
//                                        isLoading = true
//                                        // Authenticate user
//                                        auth.signInWithEmailAndPassword(email, password)
//                                            .addOnCompleteListener { task ->
//                                                if (task.isSuccessful) {
//                                                    // Login successful
//                                                    // Navigate to the next screen or perform actions
//                                                    val navigate = Intent(this@LoginActivity, LandingPage::class.java)
//                                                    startActivity(navigate)
//                                                } else {
//                                                    // Login failed
//                                                    error = task.exception?.message ?: "An unknown error occurred"
//                                                }
//                                                isLoading = false
//                                            }
//                                    } catch (e: Exception) {
//                                        // Handle exceptions
//                                        error = e.message ?: "An unknown error occurred"
//                                        isLoading = false
//                                    }
//                                }
//                            }
//                        }
//                    )
//
//                    error?.let {
//                        TextComponent(
//                            value = it,
//                            fontStyletxt = FontStyle.Normal,
//                            fweight = FontWeight.Normal,
//                            size = 14,
//                            minheight = 0,
//                            colortxt = Color.Red,
//                            alignment = TextAlign.Center
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
