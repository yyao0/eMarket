package com.example.emarket.model.local.entity

import android.os.Parcel
import android.os.Parcelable

data class Subcategory(
    val subcategoryId: String,
    val name: String,
    val categoryId: String,
    val imageUrl: String,
    val isActive: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subcategoryId)
        parcel.writeString(name)
        parcel.writeString(categoryId)
        parcel.writeString(imageUrl)
        parcel.writeString(isActive)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Subcategory> {
        override fun createFromParcel(parcel: Parcel): Subcategory {
            return Subcategory(parcel)
        }

        override fun newArray(size: Int): Array<Subcategory?> {
            return arrayOfNulls(size)
        }
    }
}
