package com.example.taxeazy.app

import com.example.taxeazy.models.CaData
import com.example.taxeazy.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpCa {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUpCa(caData: CaData) {
        // Use Firebase Auth to create user with email and password
        auth.createUserWithEmailAndPassword(caData.email, caData.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    val userId = firebaseUser?.uid

                    if (userId != null) {
                        // Save additional user data to Firestore
                        firestore.collection("ca").document(userId)
                            .set(caData)
                            .addOnSuccessListener {
                                // Data saved successfully
                                println("Data saved successfully")
                            }
                            .addOnFailureListener { e ->
                                // Error saving data
                                println("Error saving Data: $e")
                            }
                    }
                } else {
                    // Error creating user
                    println("Error creating user: ${task.exception}")
                }
            }
    }
}