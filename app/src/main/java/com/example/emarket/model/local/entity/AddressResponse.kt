package com.example.emarket.model.local.entity

data class AddressResponse(
    val addresses: List<Addresse>,
    val message: String,
    val status: Int
)