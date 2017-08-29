package com.raythinks.kotlin

import android.app.Application
import com.chenenyu.router.Router

/**
 * 注：1.组件化开发：http://www.jianshu.com/p/186fa07fc48a
 *     2.Anko for Android：http://www.jianshu.com/p/7cf5b42eb25f
 *     3.
 * Created by 赵海 on 2017/8/20.
 */
class ApplicationImpl : Application() {
    override fun onCreate() {
        super.onCreate()
        initRouter()

    }

    /**
     * 初始化路由器
     */
    private fun initRouter() {
        //http://www.jianshu.com/p/79e9a54e85b2
        // 开启log，要放到前面才能看到初始化过程的log
        Router.setDebuggable(BuildConfig.DEBUG)
        // 初始化
        Router.initialize(this);
    }
}