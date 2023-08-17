package com.example.emarket.presenter

import com.example.emarket.model.local.entity.Addresse

interface CheckoutDeliveryContract {
    interface View{
        fun displayAddresses(addresses: List<Addresse>)
        fun navigateToAddAddress()
        fun navigateToNext()
    }
    interface Presenter{
        fun getUserId():String
        fun getAddressesRemote(userId: String)
        fun addAddress(userId: String, title: String, address: String)
        fun saveSelectedAddress(address: Addresse)
    }
    interface ReponseCallback{
        fun onResponse(status: Int, message: String, address: List<Addresse>?)
        fun onError(errorMessage: String)
    }
}