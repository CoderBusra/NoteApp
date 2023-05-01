package com.task.noteapp.util

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.task.noteapp.R

@BindingAdapter("bitmap")
fun uriUrl(view: ImageView, byteArray: ByteArray?) {

    val bmp = byteArray?.let {
        it?.size?.let { it1 ->
            BitmapFactory.decodeByteArray(
                byteArray, 0,
                it1
            )
        }
    }
    bmp?.let {
        val options = RequestOptions()
            .placeholder(placeholderProgressBar(view.context))
            .error(R.color.white)

        Glide.with(view.context)
            .setDefaultRequestOptions(options)
            .load(bmp).transform(CircleCrop())
            .into(view)
    }

}

@BindingAdapter("visibility")
fun visibility(view: ImageView, edit: Boolean) {
    if (edit)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("visibility")
fun visibility(view: ImageButton, edit: Int) {
    if (edit==0)
        view.visibility = View.GONE
    else
        view.visibility = View.VISIBLE
}

@BindingAdapter("gone")
fun gone(view: ImageButton, edit: Int) {
    if (edit>0)
        view.visibility = View.GONE
    else
        view.visibility = View.VISIBLE
}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
