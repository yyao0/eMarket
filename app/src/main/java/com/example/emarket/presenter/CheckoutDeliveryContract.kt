package com.example.emarket.presenter

import com.example.emarket.model.local.entity.Addresse

interface CheckoutDeliveryContract {
    interface View{
        fun displayAddresses(addresses: List<Addresse>)
    }
    interface Presenter{
        fun getUserId():String
        fun getAddressesRemote(userId: String)
    }
    interface ReponseCallback{
        fun onResponse(status: Int, message: String, address: List<Addresse>?)
        fun onError(errorMessage: String)
    }
}