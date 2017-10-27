//package com.raythinks.poesia.di.module
//
//import android.content.Context
//import com.raythinks.poesia.ApplicationImpl
//import dagger.Module
//import dagger.Provides
//import javax.inject.Singleton
//
//
//
///**
// * 功能：<br>
// * 作者：zh<br>
// * 时间： 2017/9/21 0021<br>.
// * 版本：1.2.0
// */
//@Module class AppModule(mApp: ApplicationImpl) {
//    var app: ApplicationImpl = mApp
//    @Provides
//    @Singleton
//    fun provideApplicationContext(): Context {
//        return app
//    }
//}