package com.example.taxeazy.models

import com.google.firebase.firestore.FirebaseFirestore

data class CaData (
    val username: String,
    val uin: String,
    val email: String,
    val status: Boolean,
    val mobileNo: String,
    val location: Double,
    val language: String,
    val currentApplication: List<String> = listOf(),
    val notify: List<String> = listOf(),
    val reported: List<String> = listOf()
)

fun createCA(ca : CaData, db : FirebaseFirestore) {

}
