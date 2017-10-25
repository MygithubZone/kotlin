package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.PoesiaTranslateAdapter
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/10/11 0011<br>.
 * 版本：1.2.0
 */
class PoesiaTranslateFragment : BaseVMFragment<PoesiaDetialViewModel>() {
    lateinit var adapter: PoesiaTranslateAdapter
    override fun initView() {
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = PoesiaTranslateAdapter(viewModel)
        recyclerview.setAdapter(adapter)
    }

    override fun initData() {
        viewModel.getFanYi().observe(this, Observer {
            var fanyis = it?.fanyis
            if (fanyis == null||fanyis.size==0) {
                stl_list.showEmpty("该诗文还未添加翻译哟", { initData() })
                stl_list.btn_empty_retry.visibility = View.GONE
            } else {
                adapter.updateData(fanyis)
            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_list
}