package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.raythinks.base.statusview.StatusLayout
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.net.ApiRefranesList
import com.raythinks.poesia.ui.adapter.RefranesAdapter
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_refranes.*


/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class RefranesFragment : BaseVMFragment<RefranesViewModel>(), OnRefreshListener, OnLoadmoreListener {
    lateinit var adapter: RefranesAdapter
    var currentP: Int = 1
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        isInitRefresh = false
        viewModel.updateRefranesList(currentP + 1)
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
        currentP = 1
        isInitRefresh = true
        viewModel.updateRefranesList(currentP)
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
        isInitData = false
        stl.showLoading()
        viewModel.updateRefranesList(currentP).observe(this, Observer {
                stl.showContent()
            adapter.updateData(isInitRefresh, it!!)
            if (!isInitRefresh) {
                currentP = currentP + 1
            }
            isInitData = true
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiRefranesList -> {
                    isInitData = true
                    stl.showError()
                    return@Observer
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_refranes
}