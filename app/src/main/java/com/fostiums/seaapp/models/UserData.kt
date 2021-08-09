package com.fostiums.seaapp.models

data class UserData(
    val created_at: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val role: Int,
    val updated_at: String
)