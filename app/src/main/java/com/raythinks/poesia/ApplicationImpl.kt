package com.raythinks.poesia

import com.raythinks.base.BaseApp
import com.raythinks.poesia.di.module.AppModule


/**
 * 注：1.组件化开发：http://www.jianshu.com/p/186fa07fc48a
 *     2.Anko for Android：http://www.jianshu.com/p/7cf5b42eb25f
 *     3.
 * Created by zh on 2017/8/20.
 */
class ApplicationImpl : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext as ApplicationImpl))
                .build()
    }

}