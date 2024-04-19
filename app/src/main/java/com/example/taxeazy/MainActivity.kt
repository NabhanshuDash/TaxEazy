package com.example.taxeazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        FirebaseFirestore.setLoggingEnabled(true)

        val db = FirebaseFirestore.getInstance()

        val userId = "123456789"

        val query = db.collection("users")
            .whereEqualTo("userId", userId)
            .limit(1)

        query.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    println("${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents $exception")
            }
    }
}