package com.example.taxeazy.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.taxeazy.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpUser : AppCompatActivity() {

        private val auth: FirebaseAuth = FirebaseAuth.getInstance()
        private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }
        fun signUpUser(userData: UserData, context: Context) {
            // Use Firebase Auth to create user with email and password
            val auth: FirebaseAuth = FirebaseAuth.getInstance()
            val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
            auth.createUserWithEmailAndPassword(userData.email, userData.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        val userId = firebaseUser?.uid

                        if (userId != null) {
                            // Save additional user data to Firestore
                            firestore.collection("users").document(userId)
                                .set(userData)
                                .addOnSuccessListener {
                                    // Data saved successfully
                                    println("User data saved successfully")
                                    val navigate = Intent(context, LandingPage::class.java)
                                    startActivity(navigate)

                                }
                                .addOnFailureListener { e ->
                                    // Error saving data
                                    println("Error saving user data: $e")
                                }
                        }
                    } else {
                        // Error creating user
                        println("Error creating user: ${task.exception}")
                    }
                }
        }
    }





