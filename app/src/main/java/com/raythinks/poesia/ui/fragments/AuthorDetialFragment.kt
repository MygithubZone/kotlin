package com.raythinks.poesia.ui.fragments

import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/26 0026<br>.
 * 版本：1.2.0
 */
class AuthorDetialFragment : BaseVMFragment<AuthorDetialViewModel>() {
    override fun initView() {
//        initToolbarNav(too)
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_author_detail
}