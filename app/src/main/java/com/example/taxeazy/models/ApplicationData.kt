package com.example.taxeazy.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

data class ApplicationData(
    val currentDocs: List<String>,
    val currentCA: String,
    val status: Boolean,
    val record: String,
    val payment: Boolean?,
    val date: Timestamp,
    val uid: String,
    val userId: String
)