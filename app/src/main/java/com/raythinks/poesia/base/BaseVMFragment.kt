package com.raythinks.poesia.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.base.BaseFragment
import com.raythinks.poesia.utils.TUtils

/**
 * 功能：Fragment基类<br>
 * 作者：赵海<br>
 * 时间： 2017/9/18 0018<br>.
 * 版本：1.2.0
 */
abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {
    lateinit var viewModel: VM
    lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(getLayoutId(), container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TUtils.getTClass(this))//ViewModel对象
        initView()
        initData()
    }


    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutId(): Int
}