package com.raythinks.base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.chenenyu.router.Router
import me.yokeyword.fragmentation.Fragmentation
import kotlin.properties.Delegates

/**
 * 功能：基类Application<br>
 * 作者：Herri<br>
 * 时间： 2017/8/22 0022<br>.
 * 版本：1.2.0
 */
open class BaseApp : Application() {

    companion object {
        var mContext: Context by Delegates.notNull()
    }

    init {
        mContext = this
    }

    override fun onCreate() {
        super.onCreate()
        // Fragmentation is recommended to initialize in the Application
        Fragmentation.builder()
                // show stack view. Mode: BUBBLE, SHAKE, NONE
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
//        initRouter();
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
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