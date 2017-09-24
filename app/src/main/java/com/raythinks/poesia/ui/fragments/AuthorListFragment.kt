package com.raythinks.shiwen.ui.fragment

import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.AuthorListAdapter
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import kotlinx.android.synthetic.main.fragment_authorlist.*

/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/9/19 0019<br></br>.
 * 版本：1.2.0
 */

class AuthorListFragment : BaseVMFragment<AuthorListViewModel>() {

    lateinit var adapter: AuthorListAdapter
    override fun initView() {
        adapter = AuthorListAdapter(viewModel)
        scv_author.setAdapter(adapter)
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_authorlist
}
