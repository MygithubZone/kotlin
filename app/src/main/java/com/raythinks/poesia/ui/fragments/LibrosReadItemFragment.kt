package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.text.Html
import android.text.TextUtils
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.ERROR_MEG_NET
import com.raythinks.poesia.net.ApiLibrosBookv
import com.raythinks.poesia.ui.viewmodel.LibrosReadViewModel
import kotlinx.android.synthetic.main.fragment_libors_readitem.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/10/23 0023<br>.
 * 版本：1.2.0
 */
class LibrosReadItemFragment : BaseVMFragment<LibrosReadViewModel>() {
    var id = ""
    var oldType = 0
    var isYi = true
    override fun initView() {
    }

    override fun initData() {
        stl.showLoading()
        id = arguments.getString("id");
        viewModel.updateLiborsItem(id).observe(this, Observer {
            if (TextUtils.equals(id, "${it?.tb_bookview?.id}")) {
                var yuan = it?.tb_bookview?.cont
                if (!TextUtils.isEmpty(yuan)) {
                    stl.showContent()
                    tv_yuan.text = Html.fromHtml(yuan)
                } else {
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
                var yi = it?.tb_fanyis?.bvfanyis
                if (yi != null && yi.size > 0) {
                    isYi = true
                    tv_yi.text = Html.fromHtml(yi[0].cont)
                } else {
                    isYi = false
                    tv_yi.text = "该篇古籍无译文哟~"
                }
            }
        })
        viewModel.getShowType().observe(this, Observer {
            oldType = it ?: 0
            setShow(oldType)
        })
        viewModel.onFinishError().observe(this, Observer {
            if (TextUtils.equals(id, it?.extraData) && TextUtils.equals(ApiLibrosBookv, it?.fromApi)) {
                stl.showError(it?.msg, { initData() })
            }
        })
        setShow(oldType)
    }

    override fun getLayoutId() = R.layout.fragment_libors_readitem

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {//横屏
            viewModel.setShowType(2)
        } else {//
            viewModel.setShowType(oldType)
        }
    }

    private fun setShow(type: Int) {
        when (type) {
            0 -> {
                tv_separate_line.visibility = View.GONE
                nsv_yuan.visibility = View.VISIBLE
                nsv_yi.visibility = View.GONE
            }
            1 -> {
                tv_separate_line.visibility = View.GONE
                nsv_yuan.visibility = View.GONE
                nsv_yi.visibility = View.VISIBLE
            }
            2 -> {
                if (isYi) {
                    nsv_yuan.visibility = View.VISIBLE
                    nsv_yi.visibility = View.VISIBLE
                    tv_separate_line.visibility = View.VISIBLE
                } else {
                    nsv_yuan.visibility = View.VISIBLE
                    nsv_yi.visibility = View.GONE
                    tv_separate_line.visibility = View.GONE
                }
            }
        }
    }
}