package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.AuthorMoreAdapter
import com.raythinks.poesia.ui.adapter.LibrosAdapter
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import kotlinx.android.synthetic.main.fragment_libros.*

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
            it.let {
                it!!.tb_ziliaos.let {
                    adapter.updateData(it!!.ziliaos)
                }
            }

        })
    }

    override fun getLayoutId() = R.layout.fragment_author_moreinfo
}