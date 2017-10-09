package com.raythinks.poesia.ui.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.Observer
import android.text.Html
import android.view.animation.AccelerateDecelerateInterpolator
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController
import com.mrzk.transitioncontroller.controller.animationUtils.ViewAnimationCompatUtils
import com.mrzk.transitioncontroller.controller.listener.TransitionCustomListener
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import com.raythinks.poesia.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_author_bref.*

/**
 * Created by 赵海 on 2017/10/7.
 */
class AuthorBrefFragment : BaseVMFragment<AuthorDetialViewModel>() {
    override fun initData() {
        viewModel.getAuthorMore().observe(this, Observer {
            it.let {
                tv_author_brief.text = Html.fromHtml(it!!.tb_author.cont)
                tv_author_chaodai.text = it!!.tb_author.chaodai
                ImageUtils.loadPoesiaPic(_mActivity, it!!.tb_author.pic, civ_author_header)
            }
        })
        TransitionController.getInstance().setEnterListener(object : TransitionCustomListener {
            override fun onTransitionStart(animator: Animator) {}

            override fun onTransitionEnd(animator: Animator) {
//                val mAnimator = ViewAnimationCompatUtils.createRectReveal(cv_author, 0f, cv_author.getHeight().toFloat(), ViewAnimationCompatUtils.RECT_TOP)
//                mAnimator.duration = 500
//                mAnimator.interpolator = AccelerateDecelerateInterpolator()
//                mAnimator.addListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator) {
//                        super.onAnimationEnd(animation)
//                    }
//
//                    override fun onAnimationStart(animation: Animator) {
//                        super.onAnimationStart(animation)
//                    }
//                })
//                mAnimator.start()
            }

            override fun onTransitionCancel(animator: Animator) {}
        })
        TransitionController.getInstance().show(_mActivity, _mActivity.intent)
    }

    override fun getLayoutId() = R.layout.fragment_author_bref

    override fun initView() {
    }
}