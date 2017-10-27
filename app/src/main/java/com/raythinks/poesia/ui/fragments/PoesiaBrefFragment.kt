package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.net.ApiPoesiaContent
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.fragment_poesia_bref.*

/**
 * 功能：诗文简介<br>
 * 作者：zh<br>
 * 时间： 2017/10/11 0011<br>.
 * 版本：1.2.0
 */
class PoesiaBrefFragment : BaseVMFragment<PoesiaDetialViewModel>() {
    var mingju = ""
    override fun initView() {
        mingju = _mActivity.intent.getStringExtra("mingju")
    }

    override fun initData() {
        viewModel.getGuShiWen().observe(this, Observer {
            var gushiwen = it
            if (it != null) {
                TUtils.copyText(_mActivity, tv_poesia_content)
                if (it!!.yizhuIspass) {
                    tv_yi.visibility = View.VISIBLE
                    tv_zhu.visibility = View.VISIBLE
                } else {
                    tv_zhu.visibility = View.VISIBLE
                    tv_yi.visibility = View.GONE
                }
                setGushiWenContent(it)
                tv_yi.setOnClickListener {
                    var type = "cont"
                    when (gushiwen?.showType) {
                        "cont" -> {
                            type = "yi"
                        }
                        "yi" -> {
                            type = "cont"
                        }
                        "zhu" -> {
                            type = "yizhu"
                        }
                        "yizhu" -> {
                            type = "zhu"
                        }
                    }
                    viewModel.updatePoesiaContent(gushiwen?.id.toString(), type).observe(this, Observer {
                        gushiwen?.yizhuCont = it!!
                        gushiwen?.showType = type
                        setGushiWenContent(gushiwen)
                    })

                }
                tv_zhu.setOnClickListener {
                    var type = "cont"
                    when (gushiwen?.showType) {
                        "cont" -> {
                            type = "zhu"
                        }
                        "yi" -> {
                            type = "yizhu"
                        }
                        "zhu" -> {
                            type = "cont"
                        }
                        "yizhu" -> {
                            type = "yi"
                        }
                    }
                    viewModel.updatePoesiaContent(gushiwen?.id.toString(), type).observe(this, Observer {
                        gushiwen?.yizhuCont = it!!
                        gushiwen?.showType = type
                        setGushiWenContent(gushiwen)
                    })
                }
                TUtils.setFromBottomViewVisible(cv_poesia_bref, View.VISIBLE, null)
            }
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiPoesiaContent -> {
                    TUtils.showToast(it?.msg ?: "")
                }
            }
        })
    }

    private fun setGushiWenContent(it: GushiwensItem?) {
        var isYi = false
        var isZhu = false
        var cont: String? = null;
        when (it?.showType) {
            "cont" -> {
                isYi = false
                isZhu = false
                if (TextUtils.isEmpty(mingju)) {
                    cont = it!!.cont
                } else {
                    cont = it!!.cont.replace(mingju, "<font color= '#C68350'>${mingju}</font>")
                }
            }
            "yi" -> {
                isYi = true
                isZhu = false
                if (TextUtils.isEmpty(mingju)) {
                    cont = it?.yizhuCont?.cont
                } else {
                    cont = it?.yizhuCont?.cont?.replace(mingju, "<font color= '#C68350'>${mingju}</font>")
                }

            }
            "zhu" -> {
                isYi = false
                isZhu = true
                if (TextUtils.isEmpty(mingju)) {
                    cont = it?.yizhuCont?.cont
                } else {
                    cont = it?.yizhuCont?.cont?.replace(mingju, "<font color= '#C68350'>${mingju}</font>")
                }
            }
            "yizhu" -> {
                isYi = true
                isZhu = true
                if (TextUtils.isEmpty(mingju)) {
                    cont = it?.yizhuCont?.cont
                } else {
                    cont = it?.yizhuCont?.cont?.replace(mingju, "<font color= '#C68350'>${mingju}</font>")
                }
            }
        }
        tv_poesia_content.text = Html.fromHtml(TUtils.convertPoesia(cont ?: ""))
        tv_yi.isSelected = isYi
        tv_zhu.isSelected = isZhu
    }

    override fun getLayoutId() = R.layout.fragment_poesia_bref
}