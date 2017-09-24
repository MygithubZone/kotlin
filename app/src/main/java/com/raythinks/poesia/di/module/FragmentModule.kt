//package com.raythinks.poesia.di.module
//
//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentActivity
//import com.raythinks.poesia.di.annotation.PerFragment
//import dagger.Module
//import dagger.Provides
//
///**
// * 功能：<br>
// * 作者：赵海<br>
// * 时间： 2017/9/21 0021<br>.
// * 版本：1.2.0
// */
//@Module
//class FragmentModule(fragment: Fragment) {
//    var mFragment: Fragment = fragment
//
//    @Provides
//    @PerFragment
//    fun provideActivity(): FragmentActivity = mFragment.activity
//
//}