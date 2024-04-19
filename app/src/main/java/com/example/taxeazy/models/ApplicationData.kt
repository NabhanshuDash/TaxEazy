package com.example.taxeazy.models

data class ApplicationData(
    val currentDocs: Array<String>,
    val currentCA: String,
    val status: String,
    val record: String,
    val payment: Boolean


)