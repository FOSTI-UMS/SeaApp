package com.fostiums.seaapp.models

data class CredentialResponse(
    val code: Int,
    val data: CredentialData,
    val error: Boolean,
    val message: String
)