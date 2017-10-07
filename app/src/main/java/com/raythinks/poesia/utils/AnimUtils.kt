package com.raythinks.poesia.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils

/**
 * Created by 赵海 on 2017/10/7.
 */
object AnimUtils {
    fun loadAmin(context: Context, view: View, animResId: Int, animationListener: Animation.AnimationListener? = null) {
        var anim = AnimationUtils.loadAnimation(context, animResId);
        anim.setAnimationListener(animationListener)
        view.startAnimation(anim)

    }
}