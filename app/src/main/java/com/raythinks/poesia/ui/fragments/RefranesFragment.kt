package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import com.raythinks.poesia.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.RefranesAdapter
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel
import com.raythinks.poesia.utils.AnimUtils
import kotlinx.android.synthetic.main.fragment_refranes.*


/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class RefranesFragment : BaseVMFragment<RefranesViewModel>(), OnRefreshListener, OnLoadmoreListener {
    lateinit var adapter: RefranesAdapter
    var currentP = 1;
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
    }

    override fun initView() {
        AnimUtils.loadAmin(_mActivity, cl_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = RefranesAdapter(viewModel)
        recyclerview.setAdapter(adapter)
        tv_refranes_theme.setOnClickListener {
            //            PopMenuUtils.showPopMenu(_mActivity, tv_refranes_theme, resources.getStringArray(R.array.arrayt_refranes_theme), android.support.v7.widget.PopupMenu.OnMenuItemClickListener() {
//                item ->
//                return@OnMenuItemClickListener true
//            })
        }
    }

    override fun initData() {
        viewModel.updateRefranesList(currentP, "", "").observe(this, Observer {
            it?.let {
                adapter.updateData(currentP == 1, it)
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_refranes
}