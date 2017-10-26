package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.text.Html
import com.kogitune.activity_transition.ActivityTransition
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.activitys.AuthorDetialActivity
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import com.raythinks.poesia.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_author_bref.*

/**
 * Created by zh on 2017/10/7.
 */
class AuthorBrefFragment : BaseVMFragment<AuthorDetialViewModel>() {
    override fun initData() {
        viewModel.getAuthor().observe(this, Observer {
            it.let {
                tv_author_brief.text = Html.fromHtml(it!!.cont)
                tv_author_chaodai.text = it!!.chaodai
                ImageUtils.loadPoesiaPic(_mActivity, it!!.pic, civ_author_header)
            }
        })
        ActivityTransition.with(activity!!.intent).to(civ_author_header).start((_mActivity as AuthorDetialActivity).savedInstanceState);
    }

    override fun getLayoutId() = R.layout.fragment_author_bref

    override fun initView() {
    }
}