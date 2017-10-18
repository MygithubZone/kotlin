package com.raythinks.poesia.ui.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.kogitune.activity_transition.ActivityTransition
import com.kogitune.activity_transition.ActivityTransitionLauncher
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController
import com.mrzk.transitioncontroller.controller.animationUtils.ViewAnimationCompatUtils
import com.mrzk.transitioncontroller.controller.listener.TransitionCustomListener
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.activitys.AuthorDetialActivity
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import com.raythinks.poesia.ui.viewmodel.BasePoesiaViewModel
import com.raythinks.poesia.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_author_bref.*
import kotlinx.android.synthetic.main.item_author.view.*

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
        ActivityTransition.with(activity.intent).to(civ_author_header).start((_mActivity as AuthorDetialActivity).savedInstanceState);
    }

    override fun getLayoutId() = R.layout.fragment_author_bref

    override fun initView() {
    }
}