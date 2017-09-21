package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.ReportFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.base.utils.TUtil
import com.raythinks.poesia.R
import me.yokeyword.fragmentation.SupportFragment

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
 class MainFragment<Fg : SupportFragment> : SupportFragment() {
    protected var _mBackToFirstListener: OnBackToFirstListener? = null

    lateinit var mainFrag: Fg

    interface OnBackToFirstListener {
        fun onBackToFirstFragment()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBackToFirstListener) {
            _mBackToFirstListener = context as OnBackToFirstListener
        } else {
            throw RuntimeException(context.toString() + " must implement OnBackToFirstListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        _mBackToFirstListener = null
    }

    /**
     * 处理回退事件

     * @return
     */
    override fun onBackPressedSupport(): Boolean {
        if (childFragmentManager.backStackEntryCount > 1) {
            popChild()
        } else {
            if (mainFrag is RefranesFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish()
            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener!!.onBackToFirstFragment()
            }
        }


        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, mainFrag)
        }
    }
}
