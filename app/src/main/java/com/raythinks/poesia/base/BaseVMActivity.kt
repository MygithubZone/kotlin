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

fun SmartRefreshLayout.finishRefershOrLoadMore(isRefresh: Boolean) {
    if (isRefresh) {
        if (this.isRefreshing) this.finishRefresh()
    } else {
        if (this.isLoading) this.finishLoadmore()
    }
}
abstract class BaseVMActivity<VM : ViewModel> : BaseActivity() {
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TUtils.getTClass(this))//ViewModel对象
        setContentView(getLayoutId())
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark))
        if (isSetStatusBar())
            StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark), 0)
        initView()
        initData()
    }

    open fun isSetStatusBar(): Boolean = true
    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutId(): Int

}
