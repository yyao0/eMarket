package com.example.emarket.model.local.entity

import android.os.Parcel
import android.os.Parcelable

data class Addresse(
    val address: String = "",
    val address_id: String = "",
    val title: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(address_id)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Addresse> {
        override fun createFromParcel(parcel: Parcel): Addresse {
            return Addresse(parcel)
        }

        override fun newArray(size: Int): Array<Addresse?> {
            return arrayOfNulls(size)
        }
    }
}