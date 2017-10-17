package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.PoesiaShangAdapter
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
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
            it.let {
                adapter.updateData(it!!.shangxis)
            }

        })
    }

    override fun getLayoutId() = R.layout.fragment_list
}