package com.example.emarket.model.local.entity

data class OrderResponse(
    val message: String,
    val order: Order,
    val status: Int
)