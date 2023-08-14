package com.example.emarket.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class ImagePagerAdapter(private val context: Context, private val imageUrls: List<String>) : PagerAdapter() {

    override fun getCount(): Int = imageUrls.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        Picasso.get()
            .load("$BASE_IMAGE_URL${imageUrls[position]}")
            .into(imageView)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    companion object{
        const val BASE_IMAGE_URL = "http://192.168.0.17/myshop/images/"
    }
}
