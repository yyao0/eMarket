package com.example.emarket.presenter

interface CheckoutPaymentContract {
    interface View{
        fun selectPayment()
        fun navigateToNext()
    }

    interface Presenter{
        fun savePaymentOption(payment: String)
    }
}