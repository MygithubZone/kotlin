package com.raythinks.poesia.utils

import android.os.Bundle
import com.raythinks.poesia.ui.fragments.MainFragment
import me.yokeyword.fragmentation.SupportFragment
import java.lang.reflect.ParameterizedType
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/22 0022<br>.
 * 版本：1.2.0
 */
object TUtils {
    /**
     * 泛型转化
     */
    fun <T> getTClass(vm:Any,index:Int=0): Class<T> {
        val entityClass = (vm.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[index] as Class<T>
      return  entityClass
    }
    fun <T> getT(vm:Any,index:Int=0): T{
        val entityClass = getTClass<T>(vm,index) as Class<T>
        return  entityClass.newInstance()
    }

//    fun toMainFragment(type: Int): SupportFragment {
//        var bundle: Bundle = Bundle()
//        bundle.putInt("type", type);
//        var mainFragment: MainFragment = MainFragment()
//        mainFragment.arguments = bundle
//        return mainFragment
//    }
}