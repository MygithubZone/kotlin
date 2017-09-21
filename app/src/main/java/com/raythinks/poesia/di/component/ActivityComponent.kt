package com.raythinks.poesia.di.component

/**
 * 功能：<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/9/21 0021<br></br>.
 * 版本：1.2.0
 */

import android.app.Activity

import com.raythinks.poesia.di.annotation.PerActivity
import com.raythinks.poesia.di.module.ActivityModule

import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun getActivity(): Activity
}