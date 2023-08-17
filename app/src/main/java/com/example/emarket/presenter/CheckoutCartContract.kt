package com.example.emarket.presenter

interface CheckoutCartContract {
    interface View {
        fun displayOrder()
        fun navigateToDelivery()
    }
}