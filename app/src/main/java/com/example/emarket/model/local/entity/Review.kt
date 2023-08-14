package com.example.emarket.model.local.entity

import android.os.Parcel
import android.os.Parcelable

data class Review(
    val full_name: String = "",
    val rating: String = "",
    val review: String = "",
    val review_date: String = "",
    val review_id: String = "",
    val review_title: String = "",
    val user_id: String = ""
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
        parcel.writeString(full_name)
        parcel.writeString(rating)
        parcel.writeString(review)
        parcel.writeString(review_date)
        parcel.writeString(review_id)
        parcel.writeString(review_title)
        parcel.writeString(user_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}