package com.example.taxeazy.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taxeazy.R

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxSize()
            ) {

                Button(onClick = {
                    val navigate = Intent(this@HomeActivity, SignUpCa::class.java)
                    startActivity(navigate)
                }) {

                    Text(text="Register as CA")

                }

                Button(onClick = {
                    val navigate = Intent(this@HomeActivity, SignUpUser::class.java)
                    startActivity(navigate)
                }) {

                    Text(text="Register as Business")

                }

                Button(onClick = {
                    val navigate = Intent(this@HomeActivity, LoginActivity::class.java)
                    startActivity(navigate)
                }) {

                    Text(text="Login")

                }


            }
        }
    }
}