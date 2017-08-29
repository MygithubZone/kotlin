package com.raythinks.base.statusview

import android.content.Context
import android.view.LayoutInflater
import android.view.View

/**
 * Created by Administrator on 2016/10/19.
 */

abstract class StatusView(protected var mContext: Context) {

    protected var mInflater: LayoutInflater
    lateinit var errorView: View
        protected set// 返回数据错误
    lateinit var loadingView: View
        protected set// 一般是无网络状态,需要去设置
    lateinit var emptyView: View
        protected set// 返回数据是0个

    init {
        mInflater = mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (checkResourceID(loadingViewLayoutId)) {
            loadingView = mInflater.inflate(loadingViewLayoutId, null)
        }
        if (checkResourceID(errorViewLayoutId)) {
            emptyView = mInflater.inflate(errorViewLayoutId, null)
        }
        if (checkResourceID(emptyViewLayoutId)) {
            emptyView = mInflater.inflate(emptyViewLayoutId, null)
        }
        initLoadingView()
        initEmptyView()
        initErrorView()
    }


    abstract val errorViewLayoutId: Int

    abstract val loadingViewLayoutId: Int

    abstract val emptyViewLayoutId: Int

    abstract fun initLoadingView()

    abstract fun initErrorView()

    abstract fun initEmptyView()

    private fun checkResourceID(layoutId: Int): Boolean {
        return layoutId.ushr(24) >= 2
    }
}
