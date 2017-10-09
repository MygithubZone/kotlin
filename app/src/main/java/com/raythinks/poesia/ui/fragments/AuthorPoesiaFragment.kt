package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.PoesiaAdapter
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_author_poesia.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class AuthorPoesiaFragment : BaseVMFragment<AuthorDetialViewModel>(), OnRefreshListener, OnLoadmoreListener {
    lateinit var adapter: PoesiaAdapter
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
    }

    override fun initView() {
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = PoesiaAdapter(viewModel)
        recyclerview.setAdapter(adapter)
    }

    val currentP: Int = 1

    override fun initData() {
        viewModel.updateAuthorPoeisa(currentP).observe(this, Observer {
            it?.let {
                adapter.updateData(currentP == 1, it.tb_gushiwens)
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_author_poesia
}