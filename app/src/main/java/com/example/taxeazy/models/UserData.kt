package com.example.taxeazy.models

data class UserData(
    val username: String,
    val email: String,
    val password: String,
    val mobileNo: String,
    val aadharNo: String,
    val buisnessName: String,
    val language: String,
    //val location
    val applicationId: Array<String>,
    val personalStore: Array<String>,
    val notif: Array<String>,
    val reported: Array<String>
)