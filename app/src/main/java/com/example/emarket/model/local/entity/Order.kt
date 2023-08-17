package com.example.emarket.model.local.entity

import android.os.Parcel
import android.os.Parcelable

data class Order(
    var address: String = "",
    var address_title: String = "",
    var bill_amount: String = "",
    var items: List<Item> = emptyList(),
    var order_date: String = "",
    var order_id: String = "",
    var order_status: String = "",
    var payment_method: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Item) ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(address_title)
        parcel.writeString(bill_amount)
        parcel.writeTypedList(items)
        parcel.writeString(order_date)
        parcel.writeString(order_id)
        parcel.writeString(order_status)
        parcel.writeString(payment_method)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}