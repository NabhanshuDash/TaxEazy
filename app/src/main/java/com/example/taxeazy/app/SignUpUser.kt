package com.example.taxeazy.app

import com.example.taxeazy.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpUser {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUpUser(userData: UserData) {
        // Use Firebase Auth to create user with email and password
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
