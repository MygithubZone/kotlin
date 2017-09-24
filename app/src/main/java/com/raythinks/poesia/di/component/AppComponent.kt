//package com.raythinks.poesia.di.component
//
//import android.content.Context
//import com.raythinks.poesia.di.module.AppModule
//import dagger.Component
//import javax.inject.Singleton
//
///**
// * AppComponent: 生命周期跟Application一样的组件。
// * 可注入到自定义的Application类中，@Singletion代表各个注入对象为单例。
// * 功能：<br>
// * 作者：赵海<br>
// * 时间： 2017/9/21 0021<br>.
// * 版本：1.2.0
// */
//
//@Singleton
//@Component(modules = arrayOf(AppModule::class))
//interface AppComponent{
//    fun getContext(): Context
//}