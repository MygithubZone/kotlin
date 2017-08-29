package com.raythinks.base

import android.app.Application
import com.chenenyu.router.Router

/**
 * 功能：基类Application<br>
 * 作者：Herri<br>
 * 时间： 2017/8/22 0022<br>.
 * 版本：1.2.0
 */
class BaseApp :Application(){
    override fun onCreate() {
        super.onCreate()
        initRouter();
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