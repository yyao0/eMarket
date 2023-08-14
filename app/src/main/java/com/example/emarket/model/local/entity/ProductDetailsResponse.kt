package com.example.emarket.model.local.entity

data class ProductDetailsResponse(
    val message: String,
    val product: Product,
    val status: Int
)