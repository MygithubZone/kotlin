package com.raythinks.poesia.base

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.raythinks.base.BaseActivity
import com.raythinks.poesia.utils.TUtils
import com.raythinks.poesia.R

/**
 * Activity基类：
 *
 * Created by Herri on 2017/8/20.
 */

abstract class BaseVMActivity<VM : ViewModel> : BaseActivity() {
    lateinit var viewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TUtils.getTClass(this))//ViewModel对象
        setContentView(getLayoutId())
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark))
        initView()
        initData()
    }


    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutId(): Int

}
