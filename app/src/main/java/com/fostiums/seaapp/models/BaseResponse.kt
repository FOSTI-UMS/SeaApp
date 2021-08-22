package com.fostiums.seaapp.models

data class BaseResponse(
    val code: Int,
    val error: Boolean,
    val message: String
)