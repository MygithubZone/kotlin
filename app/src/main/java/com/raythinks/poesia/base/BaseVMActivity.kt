package com.raythinks.poesia.base

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.raythinks.base.BaseActivity
import com.raythinks.poesia.utils.TUtils
import com.raythinks.poesia.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import qiu.niorgai.StatusBarCompat

/**
 * Activity基类：
 *
 * Created by Herri on 2017/8/20.
 */
/**
 * 扩展函数。主要用于SmartRefreshLayout 下拉刷新和上啦加载关闭刷新加载状态。
 */
fun SmartRefreshLayout.finishRefershOrLoadMore(isRefresh: Boolean) {
    if (isRefresh) {
        if (this.isRefreshing) this.finishRefresh()
    } else {
        if (this.isLoading) this.finishLoadmore()
    }
}

/**
 * Activity基类。
 */
abstract class BaseVMActivity<VM : ViewModel> : BaseActivity() {
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TUtils.getTClass(this))//ViewModel对象
        setContentView(getLayoutId())
        if (isSetStatusBar())
            StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark), 0)
        initView()
        initData()
    }

    //是否设置状态栏颜色。
    open fun isSetStatusBar(): Boolean = true

    //初始化view
    abstract fun initView()

    //初始化数据
    abstract fun initData()

    //布局文件xml
    abstract fun getLayoutId(): Int

}
