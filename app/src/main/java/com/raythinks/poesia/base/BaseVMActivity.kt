package com.raythinks.poesia.base

import android.arch.lifecycle.*
import android.os.Bundle
import com.raythinks.base.BaseActivity
import com.raythinks.poesia.ApplicationImpl
import com.raythinks.poesia.utils.TUtils
import me.yokeyword.fragmentation.SupportActivity
import java.lang.reflect.ParameterizedType


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
        initView()
        initData()
    }



    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutId(): Int

}
