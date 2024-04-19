package com.example.taxeazy.models

data class CaData (
    val username: String,
    val UIN: String,
    val email: String,
    val status: Boolean,
    val mobileNo: String,
    val location: Double,
    val language: String,
    val CurrentApplication: Array<String>,
    val notif: Array<String>,
    val reported: Array<String>
)