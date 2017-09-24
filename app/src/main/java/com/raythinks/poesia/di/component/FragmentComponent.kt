//package com.raythinks.poesia.di.component
//
//import android.app.Activity
//import com.raythinks.poesia.di.annotation.PerActivity
//import com.raythinks.poesia.di.annotation.PerFragment
//import com.raythinks.poesia.di.module.FragmentModule
//import com.raythinks.poesia.ui.fragments.LibrosFragment
//import com.raythinks.poesia.ui.fragments.MainFragment
//import com.raythinks.poesia.ui.fragments.PoesiaFragment
//import com.raythinks.poesia.ui.fragments.RefranesFragment
//import com.raythinks.shiwen.ui.fragment.AuthorListFragment
//import dagger.Component
//
///**
// * 功能：<br>
// * 作者：赵海<br>
// * 时间： 2017/9/21 0021<br>.
// * 版本：1.2.0
// */
//@PerActivity
//@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentModule::class))
//interface FragmentComponent{
////     fun getActivity(): Activity
//    fun inject( authorListFragment: AuthorListFragment )
//    fun inject(  librosFragment: LibrosFragment )
//    fun inject(  poesiaFragment: PoesiaFragment)
//    fun inject(  refranesFragment: RefranesFragment )
//}