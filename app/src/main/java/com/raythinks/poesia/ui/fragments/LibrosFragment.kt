package com.raythinks.poesia.ui.fragments

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.raythinks.base.BaseFragment
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.adapter.LibrosAdapter
import com.raythinks.poesia.ui.adapter.RefranesAdapter
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import com.raythinks.poesia.ui.viewmodel.LibrosViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

/**
 * Created by zh on 2017/9/20.
 */
class LibrosFragment : BaseFragment<LibrosViewModel>() , OnRefreshListener, OnLoadmoreListener {
    lateinit var adapter: LibrosAdapter
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
    }

    override fun initView() {
        refreshLayout
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = LibrosAdapter(viewModel)
        recyclerview.setAdapter(adapter)
    }

    override fun initData() {
    }
    override fun getLayoutId(): Int = R.layout.fragment_list
}