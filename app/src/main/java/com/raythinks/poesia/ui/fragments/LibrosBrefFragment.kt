package com.raythinks.libros.ui.fragments

import android.arch.lifecycle.Observer
import android.text.Editable
import android.text.Html
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.viewmodel.LibrosDetailViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.fragment_libros_bref.*

/**
 * 功能：古文简介<br>
 * 作者：zh<br>
 * 时间： 2017/10/11 0011<br>.
 * 版本：1.2.0
 */
class LibrosBrefFragment : BaseVMFragment<LibrosDetailViewModel>() {
    override fun initView() {
    }

    override fun initData() {
        viewModel.getBooks().observe(this, Observer {
            it.let {
                tv_libros_content.text = Html.fromHtml(it!!.cont) as Editable
                TUtils.copyText(context, tv_libros_content)
                TUtils.setFromBottomViewVisible(cv_libros_bref, View.VISIBLE, null)
            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_libros_bref
}