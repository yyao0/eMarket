package com.example.emarket.model.local.entity

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val average_rating: String = "",
    val category_id: String = "",
    val description: String = "",
    val images: List<Image> = emptyList(),
    val is_active: String = "",
    val price: String = "",
    val product_id: String = "",
    val product_image_url: String = "",
    val product_name: String = "",
    val reviews: List<Review> = emptyList(),
    val specifications: List<Specification> = emptyList(),
    val sub_category_id: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Image) ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Review) ?: emptyList(),
        parcel.createTypedArrayList(Specification) ?: emptyList(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(average_rating)
        parcel.writeString(category_id)
        parcel.writeString(description)
        parcel.writeTypedList(images)
        parcel.writeString(is_active)
        parcel.writeString(price)
        parcel.writeString(product_id)
        parcel.writeString(product_image_url)
        parcel.writeString(product_name)
        parcel.writeTypedList(reviews)
        parcel.writeTypedList(specifications)
        parcel.writeString(sub_category_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}