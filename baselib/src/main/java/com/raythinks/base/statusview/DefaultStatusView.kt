package com.raythinks.base.statusview

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.widget.TextView
import com.raythinks.base.R
import kotlinx.android.synthetic.main.error_view.view.*

/**
 * 默认状态viewLayout
 */

class DefaultStatusView : StatusView {
    override val errorViewLayoutId: Int = R.layout.error_view
    override val loadingViewLayoutId: Int = R.layout.loading_view
    override val emptyViewLayoutId: Int = R.layout.empty_view

    constructor(context: Context) : super(context) {
    }

    override fun initLoadingView() {
        loadingView = mInflater.inflate(loadingViewLayoutId, null)
    }

    override fun initErrorView() {
        errorView = mInflater.inflate(errorViewLayoutId, null)

    }

    override fun initEmptyView() {
        emptyView = mInflater.inflate(emptyViewLayoutId, null)
    }
}
