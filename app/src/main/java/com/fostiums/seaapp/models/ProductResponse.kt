package com.fostiums.seaapp.models

data class ProductResponse(
    val code: Int,
    val `data`: List<ProductData>,
    val error: Boolean,
    val message: String
)