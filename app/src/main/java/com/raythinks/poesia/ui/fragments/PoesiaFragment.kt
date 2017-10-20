package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kogitune.activity_transition.ActivityTransitionLauncher
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.net.ApiPoesiaList
import com.raythinks.poesia.ui.activitys.PoesiaDetialActivity
import com.raythinks.poesia.ui.adapter.PoesiaAdapter
import com.raythinks.poesia.ui.viewmodel.PoesiaViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_poesia.*
import kotlinx.android.synthetic.main.item_poesia.view.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class PoesiaFragment : BaseVMFragment<PoesiaViewModel>(), OnItemClickListener {
    override fun onItemClick(position: Int, itemView: View) {
        var intent = Intent(_mActivity, PoesiaDetialActivity::class.java)
        intent.putExtra("poesiaItem", adapter.data[position])
        ActivityTransitionLauncher.with(_mActivity).from(itemView.tv_poesia_content).launch(intent);
    }

    lateinit var adapter: PoesiaAdapter

    override fun initView() {
        AnimUtils.loadAmin(_mActivity, cl_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = PoesiaAdapter(viewModel, this)
        recyclerview.setAdapter(adapter)
        refreshLayout.setOnRefreshListener {
            isInitRefresh = true
            viewModel.updatePoesiaList(1, "", "")
        }
        refreshLayout.setOnLoadmoreListener {
            isInitRefresh = false
            viewModel.updatePoesiaList(currentP + 1, "", "")
        }
    }

    var currentP: Int = 1
    var sumP: Int = 1
    override fun initData() {
        stl.showLoading()
        viewModel.updatePoesiaList(currentP, "", "").observe(this, Observer {
            var gushiwens = it?.gushiwens
            currentP = it?.currentPage ?: 1
            sumP = it?.sumPage ?: 1
            if (gushiwens == null || gushiwens.size == 0) {//返回列表为空
                if (it?.currentPage == 1) {//返回列表为空,且第一页
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
            } else {//返回列表不为空
                if (it?.currentPage == 1) {//第一页
                    stl.showContent()
                }
                refreshLayout.setEnableLoadmore(currentP < sumP)
                adapter.updateData(isInitRefresh, gushiwens)
            }
            refreshLayout.finishRefershOrLoadMore(currentP == 1)
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiPoesiaList -> {
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

    override fun getLayoutId(): Int = R.layout.fragment_poesia
}