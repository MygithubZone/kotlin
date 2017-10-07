package com.raythinks.poesia.utils

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.raythinks.poesia.ApplicationImpl
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.tab_textview_style.view.*
import kotlinx.android.synthetic.main.toast_style1.view.*
import me.yokeyword.fragmentation.SupportFragment
import java.lang.reflect.ParameterizedType


/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/22 0022<br>.
 * 版本：1.2.0
 */
object TUtils {
    lateinit var toast: Toast

    init {
        initToast()
    }

    /**
     *
     */
   private fun initToast() {
        toast = Toast(ApplicationImpl.app);
        // 获取LayoutInflater对象
        val inflater = ApplicationImpl.app.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // 由layout文件创建一个View对象
        val layout = inflater.inflate(R.layout.toast_style1, null)
        // 吐司上的文字
        toast.view = layout

    }

    fun showToast(toastStr: String, duration: Int = Toast.LENGTH_SHORT, gravity: Int = Gravity.BOTTOM) {
        toast.setGravity(gravity, 0, 100)
        toast.setDuration(duration)
//        toast.cancel()
        toast.view.toast_text.text = toastStr
        toast.show()
    }

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

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}