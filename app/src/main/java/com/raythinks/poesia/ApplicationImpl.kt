package com.raythinks.poesia

import com.raythinks.base.BaseApp


/**
 * 注：1.组件化开发：http://www.jianshu.com/p/186fa07fc48a
 *     2.Anko for Android：http://www.jianshu.com/p/7cf5b42eb25f
 *     3.
 * Created by zh on 2017/8/20.
 */
class ApplicationImpl : BaseApp() {
    companion object {
      lateinit  var app: ApplicationImpl
//        lateinit var appComponent: AppComponent
    }

    init {
//        appComponent = DaggerAppComponent.builder()
//                .appModule(AppModule(applicationContext as ApplicationImpl))
//                .build()
        app = this;
    }

    override fun onCreate() {
        super.onCreate()
    }

//     fun getppComponent(): AppComponent {
//        return appComponent
//    }
}