package com.raythinks.poesia.ui.fragments

import com.raythinks.poesia.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.RefranesAdapter
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class RefranesVMFragment : BaseVMFragment<RefranesViewModel>(), OnRefreshListener, OnLoadmoreListener {
    lateinit var adapter: RefranesAdapter
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
    }

    override fun initView() {
        refreshLayout
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = RefranesAdapter(viewModel)
        recyclerview.setAdapter(adapter)
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_list
}