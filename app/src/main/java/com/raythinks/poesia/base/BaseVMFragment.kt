package com.raythinks.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment

/**
 * 功能：Fragment基类<br>
 * 作者：赵海<br>
 * 时间： 2017/9/18 0018<br>.
 * 版本：1.2.0
 */
abstract class BaseFragment<VM : BaseViewModel> : SupportFragment() {
    lateinit var viewModel: VM
    lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(getLayoutId(), container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutId(): Int
}