package com.example.emarket.model.local.entity

import android.os.Parcel
import android.os.Parcelable

data class Specification(
    val display_order: String = "",
    val specification: String = "",
    val specification_id: String = "",
    val title: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()  ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(display_order)
        parcel.writeString(specification)
        parcel.writeString(specification_id)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Specification> {
        override fun createFromParcel(parcel: Parcel): Specification {
            return Specification(parcel)
        }

        override fun newArray(size: Int): Array<Specification?> {
            return arrayOfNulls(size)
        }
    }
}