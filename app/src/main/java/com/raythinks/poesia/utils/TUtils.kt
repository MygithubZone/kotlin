package com.raythinks.poesia.utils

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.fragment_authorlist.*
import kotlinx.android.synthetic.main.tab_textview_style.view.*
import me.yokeyword.fragmentation.SupportFragment
import java.lang.reflect.ParameterizedType


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
    fun <T> getTClass(vm: Any, index: Int = 0): Class<T> {
        val entityClass = (vm.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[index] as Class<T>
        return entityClass
    }

    fun <T> getT(vm: Any, index: Int = 0): T {
        val entityClass = getTClass<T>(vm, index) as Class<T>
        return entityClass.newInstance()
    }

    fun toMainFragment(type: Int): SupportFragment {
        var bundle: Bundle = Bundle()
        bundle.putInt("type", type);
        var mainFragment: MainFragment = MainFragment()
        mainFragment.arguments = bundle
        return mainFragment
    }

    /**
     * 设置tab
     */
    fun setTab(context: Context, strs: Array<String>, tab: TabLayout) {
        for (str in strs) {
            val tabView = LayoutInflater.from(context).inflate(R.layout.tab_textview_style, null)
            tabView.tv_tab_item.setText(str)
            tab.addTab(tab.newTab().setCustomView(tabView))
        }
    }
}