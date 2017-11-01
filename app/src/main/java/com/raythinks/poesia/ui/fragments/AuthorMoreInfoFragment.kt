package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.AuthorMoreAdapter
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.fragment_author_poesia.*

/**
 * Created by zh on 2017/10/7.
 */
class AuthorMoreInfoFragment : BaseVMFragment<AuthorDetialViewModel>() {
    lateinit var adapter: AuthorMoreAdapter
    override fun initView() {
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = AuthorMoreAdapter(viewModel)
        recyclerview.setAdapter(adapter)
    }

    override fun initData() {
        viewModel.getAuthorMore().observe(this, Observer {
            var zhiliaos = it?.tb_ziliaos?.ziliaos
            if (zhiliaos != null && zhiliaos.size > 0) {
                stl.showContent()
                adapter.updateData(zhiliaos)
            } else {
                stl.showEmpty("暂时未添加该诗人任何信息哟", { initData() })
                stl.btn_empty_retry.visibility = View.GONE
            }

        })
    }

    override fun getLayoutId() = R.layout.fragment_author_moreinfo
}