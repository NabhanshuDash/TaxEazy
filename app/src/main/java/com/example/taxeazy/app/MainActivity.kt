package com.example.taxeazy.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taxeazy.components.CButton
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
//        FirebaseFirestore.setLoggingEnabled(true)
//
//        val db = FirebaseFirestore.getInstance()
//
//        val userId = "123456789"
//
//        val query = db.collection("users")
//            .whereEqualTo("userId", userId)
//            .limit(1)
//
//        query.get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    println("${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                println("Error getting documents $exception")
//            }
        setContent {
            Surface(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                CButton(
                    text = "Click",
                    onClick = {
                        val navigate = Intent(this@MainActivity, SignUpUser::class.java)
                        startActivity(navigate)
                    }
                )
            }
        }
    }


}