package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.net.ApiRefranesList
import com.raythinks.poesia.ui.adapter.RefranesAdapter
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.fragment_refranes.*


/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class RefranesFragment : BaseVMFragment<RefranesViewModel>() {
    lateinit var adapter: RefranesAdapter
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
        refreshLayout.setOnRefreshListener {
            isInitRefresh = true
            viewModel.updateRefranesList(1)
        }
        refreshLayout.setOnLoadmoreListener {
            isInitRefresh = false
            viewModel.updateRefranesList(currentP + 1)
        }
    }

    var currentP: Int = 1
    var sumP: Int = 1
    override fun initData() {
        stl.showLoading()
        viewModel.updateRefranesList(1).observe(this, Observer {
            var mingjus = it?.mingjus
            currentP = it?.currentPage ?: 1
            sumP = it?.sumPage ?: 1
            if (mingjus == null || mingjus.size == 0) {
                if (it?.currentPage == 1) {
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
            } else {
                if (it?.currentPage == 1) {
                    stl.showContent()
                }
                refreshLayout.setEnableLoadmore(currentP < sumP)
                adapter.updateData(isInitRefresh, mingjus)
            }
            refreshLayout.finishRefershOrLoadMore(currentP == 1)
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiRefranesList -> {
                    TUtils.showToast(it?.msg ?: "aaaa")
                    if (currentP == 1) {
                        stl.showError(it.msg, { initData() })
                    }
                    refreshLayout.finishRefershOrLoadMore(currentP == 1)
                    return@Observer
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_refranes
}