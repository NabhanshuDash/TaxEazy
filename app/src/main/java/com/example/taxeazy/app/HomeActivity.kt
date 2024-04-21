package com.example.taxeazy.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taxeazy.R
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : ComponentActivity() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is already logged in
        if (auth.currentUser != null) {
            // User is already logged in, redirect to appropriate screen
            redirectToScreen()
            return
        }

        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {
                        val navigate = Intent(this@HomeActivity, SignUpCa::class.java)
                        startActivity(navigate)
                    },
                    modifier = Modifier.height(72.dp)
                ) {
                    Text(text = "Register as CA")
                }

                Button(
                    onClick = {
                        val navigate = Intent(this@HomeActivity, SignUpUser::class.java)
                        startActivity(navigate)
                    },
                    modifier = Modifier.height(72.dp)
                ) {
                    Text(text = "Register as Business")
                }

                Button(
                    onClick = {
                        val navigate = Intent(this@HomeActivity, LoginActivity::class.java)
                        startActivity(navigate)
                    },
                    modifier = Modifier.height(72.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }
    }

    private fun redirectToScreen() {

        val navigate = Intent(this@HomeActivity, LandingPageUser::class.java)
        startActivity(navigate)
        finish() // Finish the current activity to prevent user from coming back to HomeActivity
    }
}

@Preview
@Composable
fun showHomePreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {

            },
            modifier = Modifier.height(72.dp)
        ) {
            Text(text = "Register as CA")
        }

        Button(
            onClick = {

            },
            modifier = Modifier.height(72.dp)
        ) {
            Text(text = "Register as Business")
        }

        Button(
            onClick = {

            },
            modifier = Modifier.height(72.dp)
        ) {
            Text(text = "Login")
        }
    }
}