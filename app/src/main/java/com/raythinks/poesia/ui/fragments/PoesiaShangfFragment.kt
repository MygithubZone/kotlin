package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.PoesiaShangAdapter
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/10/11 0011<br>.
 * 版本：1.2.0
 */
class PoesiaShangfFragment : BaseVMFragment<PoesiaDetialViewModel>() {

    lateinit var adapter: PoesiaShangAdapter
    override fun initView() {
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = PoesiaShangAdapter(viewModel)
        recyclerview.setAdapter(adapter)
    }

    override fun initData() {
        viewModel.getShangXi().observe(this, Observer {
            var shangxis = it?.shangxis
            if (shangxis == null||shangxis.size==0) {
                stl_list.showEmpty("该诗文还未添加赏析哟", { initData() })
                stl_list.btn_empty_retry.visibility = View.GONE
            } else {
                adapter.updateData(shangxis)
            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_list
}