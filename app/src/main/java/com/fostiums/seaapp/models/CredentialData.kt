package com.fostiums.seaapp.models

data class CredentialData(
    val expires_in: Any,
    val token: String,
    val token_type: String,
    val user_data: UserData
)