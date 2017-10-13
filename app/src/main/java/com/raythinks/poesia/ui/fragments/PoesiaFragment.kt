package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.activitys.PoesiaDetialActivity
import com.raythinks.poesia.ui.adapter.PoesiaAdapter
import com.raythinks.poesia.ui.viewmodel.PoesiaViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_poesia.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class PoesiaFragment : BaseVMFragment<PoesiaViewModel>(), OnRefreshListener, OnLoadmoreListener, OnItemClickListener {
    override fun onItemClick(position: Int, itemView: View) {
        var intent = Intent(_mActivity, PoesiaDetialActivity::class.java)
        intent.putExtra("poesiaItem", adapter.data[position])
        startActivity(intent)
    }

    lateinit var adapter: PoesiaAdapter
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
    }

    override fun initView() {
        AnimUtils.loadAmin(_mActivity, cl_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = PoesiaAdapter(viewModel, this)
        recyclerview.setAdapter(adapter)
    }

    val currentP: Int = 1

    override fun initData() {
        viewModel.updatePoesiaList(currentP, "", "").observe(this, Observer {
            it?.let {
                adapter.updateData(currentP == 1, it)
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_poesia
}