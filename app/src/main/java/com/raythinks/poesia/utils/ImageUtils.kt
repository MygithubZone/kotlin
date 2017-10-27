package com.raythinks.poesia.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.raythinks.poesia.R

/**
 * Created by zh on 2017/10/7.
 */
object ImageUtils {
    fun loadPic(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    fun loadPoesiaPic(context: Context, url: String?, imageView: ImageView) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(R.mipmap.ic_launcher_round)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load("http://img.gushiwen.org/authorImg/" + url + ".jpg")
                    .into(imageView);
        }
    }
}