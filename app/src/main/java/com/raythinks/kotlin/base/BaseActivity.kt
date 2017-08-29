package com.raythinks.kotlin.base

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.raythinks.kotlin.model.User
import java.lang.reflect.ParameterizedType


/**
 * Activity基类：
 *
 * Created by Herri on 2017/8/20.
 */

open class BaseActivity<VM : ViewModel> : AppCompatActivity(), LifecycleOwner {

    var lifecycle = LifecycleRegistry(this)//生命周期注册对象
    var viewModel: VM = ViewModelProviders.of(this).get(getVM())//ViewModel对象
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
    }
}
