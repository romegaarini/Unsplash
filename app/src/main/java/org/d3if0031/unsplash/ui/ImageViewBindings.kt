package org.d3if0031.unsplash.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("app:load")
fun imageViewLoadUrl(imageView: ImageView, url: String?) {
    if (url == null) return
    Glide.with(imageView.context).load(url)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imageView)
}

@BindingAdapter("app:loadCircular")
fun imageViewLoadUrlCircular(imageView: ImageView, url: String?) {
    if (url == null) return
    Glide.with(imageView.context).load(url)
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imageView)
}
