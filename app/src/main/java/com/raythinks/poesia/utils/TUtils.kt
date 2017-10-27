package com.raythinks.poesia.utils

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.jaeger.library.OnSelectListener
import com.jaeger.library.OnSelectTextCallBack
import com.jaeger.library.SelectableTextHelper
import com.raythinks.poesia.ApplicationImpl
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.app_bar_main.*
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

    fun initToolbarNav(toolbar: Toolbar, _mActivity: AppCompatActivity) {
        _mActivity.setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_72dp)
        toolbar.setNavigationOnClickListener { v -> _mActivity.finish() }
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
        var mainFragment = MainFragment()
        mainFragment.arguments = bundle
        return mainFragment
    }

    /**
     * 设置tab
     */
    fun setTab(context: Context, strs: Array<String>, tab: TabLayout, textSize: Float = 14f) {
        for (i in strs.indices) {
            val tabView = LayoutInflater.from(context).inflate(R.layout.tab_textview_style, null)
            tabView.tv_tab_item.textSize = textSize
            tabView.tv_tab_item.setText(strs[i])
            var tabItem = tab.getTabAt(i)
            if (tabItem == null) {
                tabItem = tab.newTab()
                tabItem.setCustomView(tabView)
                tab.addTab(tabItem)
            } else {
                tabItem.setCustomView(tabView)
            }
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun isEmptyList(list: List<Any>): Boolean {
        return list == null || list.size == -0
    }

    /**
     * 底部组件显示和隐藏
     *
     * @param view     底部组件
     * @param visible  显示：View.VISIBLE和隐藏 View.GONE
     * @param listener 动画监听
     */
    fun setBottomViewVisible(view: View, visible: Int, listener: Animation.AnimationListener?) {
        if (visible == View.VISIBLE) {
            val animFadeIn = AnimationUtils.loadAnimation(view.context,
                    R.anim.in_from_bottom)
            view.visibility = visible
            if (listener != null)
                animFadeIn.setAnimationListener(listener)
            view.startAnimation(animFadeIn)

        } else if (visible == View.GONE) {
            val animFadeOut = AnimationUtils.loadAnimation(view.context,
                    R.anim.out_from_bottom)
            if (listener != null)
                animFadeOut.setAnimationListener(listener)
            view.startAnimation(animFadeOut)
            view.visibility = visible
        }

    }

    fun setFromBottomViewVisible(view: View, visible: Int, listener: Animation.AnimationListener?) {
        val animFadeIn = AnimationUtils.loadAnimation(view.context,
                R.anim.in_from_bottom)
        view.visibility = visible
        if (listener != null)
            animFadeIn.setAnimationListener(listener)
        view.startAnimation(animFadeIn)
    }

    fun copyText(context: Context, mTv: TextView): SelectableTextHelper{
        var mSelectableTextHelper = SelectableTextHelper.Builder(mTv)
                .setSelectedColor(ContextCompat.getColor(context, R.color.selected_yellow))
                .setCursorHandleSizeInDp(20f)
                .setCursorHandleColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .build()
        mSelectableTextHelper.setSelectListener(OnSelectTextCallBack(context))
        return mSelectableTextHelper
    }

    fun setFromTopViewAnim(view: View, visible: Int, listener: Animation.AnimationListener?) {
        val animFadeIn = AnimationUtils.loadAnimation(view.context,
                R.anim.in_from_top)
        view.visibility = visible
        if (listener != null)
            animFadeIn.setAnimationListener(listener)
        view.startAnimation(animFadeIn)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}