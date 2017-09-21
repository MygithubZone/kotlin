package com.raythinks.poesia.di.module

import android.app.Activity
import com.raythinks.poesia.di.annotation.PerActivity
import dagger.Module
import dagger.Provides

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
@Module
class ActivityModule constructor(activity: Activity) {
    var act: Activity = activity

    @Provides
    @PerActivity
    fun provideActivity(): android.app.Activity = act

}