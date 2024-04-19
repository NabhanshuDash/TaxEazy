package com.example.taxeazy

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseFirestore.setLoggingEnabled(true)

        val db = FirebaseFirestore.getInstance()

        val userId = "123456789"

        val query = db.collection("users")
            .whereEqualTo("userId", userId)
            .limit(1)

        query.get()
            .addOnSuccessListener {documents ->
                for(document in documents) {
                    println("${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents $exception")
            }
    }
}