package com.nsikakthompson.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nsikakthompson.widget.InfoView

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("title")
fun bindTitle(view: InfoView, title: String){
    view.textViewTitle.text = title
}

@BindingAdapter("description")
fun bindDescription(view: InfoView, description: String){
    view.textViewDesc.text = description
}