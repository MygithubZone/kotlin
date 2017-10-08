package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.text.Html
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import kotlinx.android.synthetic.main.fragment_author_bref.*

/**
 * Created by 赵海 on 2017/10/7.
 */
class AuthorBrefFragment : BaseVMFragment<AuthorDetialViewModel>() {
    override fun initData() {
        viewModel.getAuthorMore().observe(this, Observer {
            it.let {
                tv_author_brief.text = Html.fromHtml(it!!.tb_author.cont)
            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_author_bref

    override fun initView() {
    }
}