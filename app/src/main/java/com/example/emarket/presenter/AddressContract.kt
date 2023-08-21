package com.example.emarket.presenter

import com.example.emarket.model.local.entity.Addresse

interface AddressContract{
    interface View{
        fun displayAddresses(addresses: List<Addresse>)
        fun navigateToAddAddress()
    }
    interface Presenter{
        fun getUserId():String
        fun getAddressesRemote(userId: String)
        fun addAddress(userId: String, title: String, address: String)
    }
}