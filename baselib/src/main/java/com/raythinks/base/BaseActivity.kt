package com.raythinks.base

import android.arch.lifecycle.*
import android.os.Bundle
import me.yokeyword.fragmentation.SupportActivity
import java.lang.reflect.ParameterizedType


/**
 * Activity基类：
 *
 * Created by Herri on 2017/8/20.
 */

abstract class BaseActivity<VM : ViewModel> : SupportActivity(), LifecycleOwner {
    lateinit var mContext: SupportActivity
    var lifecycle = LifecycleRegistry(this)//生命周期注册对象
    lateinit var viewModel: VM
    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }

    /**
     * 泛型转化
     */
    fun getVM(): Class<VM> {
        val genType = javaClass.genericSuperclass
        val params = (genType as ParameterizedType).getActualTypeArguments()
        return params[0] as Class<VM>

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        viewModel = ViewModelProviders.of(this).get(getVM())//ViewModel对象
        setContentView(getLayoutId())
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutId(): Int

}
