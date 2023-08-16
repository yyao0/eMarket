package com.example.emarket.model.local.entity

import android.os.Parcel
import android.os.Parcelable

data class Item(
    var amount: String = "",
    var description: String = "",
    var product_id: String = "",
    var product_image_url: String = "",
    var product_name: String = "",
    var quantity: String = "",
    var unit_price: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(amount)
        parcel.writeString(description)
        parcel.writeString(product_id)
        parcel.writeString(product_image_url)
        parcel.writeString(product_name)
        parcel.writeString(quantity)
        parcel.writeString(unit_price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}