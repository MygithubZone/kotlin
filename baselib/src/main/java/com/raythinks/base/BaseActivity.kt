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

abstract class BaseActivity  : SupportActivity(), LifecycleOwner {
    lateinit var mContext: SupportActivity
    var lifecycle = LifecycleRegistry(this)//生命周期注册对象
    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
}
