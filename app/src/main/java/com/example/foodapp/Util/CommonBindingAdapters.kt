package com.example.foodapp.Util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodapp.R


@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, url: String){
    imageView.load(url){
        crossfade(true)
        placeholder(R.drawable.ic_baseline_image_24)
        error(R.drawable.ic_baseline_broken_image_24)
    }
}

