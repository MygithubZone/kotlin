package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.text.Html
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import com.raythinks.poesia.utils.ImageUtils
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.fragment_author_bref.*

/**
 * Created by zh on 2017/10/7.
 */
class PoesiaAuthorFragment : BaseVMFragment<PoesiaDetialViewModel>() {
    override fun initData() {
        viewModel.getAuthor().observe(this, Observer {
            var author=it?.cont
            if (author == null) {
                stl_poesia_author.showEmpty("暂时未添加该诗文作者哟", { initData() })
                stl_poesia_author.btn_empty_retry.visibility = View.GONE
            } else {
                tv_author_brief.text = Html.fromHtml(it!!.cont)
                tv_author_chaodai.text = it!!.chaodai + it.nameStr
                ImageUtils.loadPoesiaPic(_mActivity, it!!.pic, civ_author_header)

            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_author_bref

    override fun initView() {
    }
}