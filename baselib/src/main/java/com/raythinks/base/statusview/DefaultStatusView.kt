package com.raythinks.base.statusview

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.widget.TextView
import com.raythinks.base.R

/**
 * 默认状态viewLayout
 */

class DefaultStatusView(context: Context) : StatusView(context) {
    override val errorViewLayoutId: Int = R.layout.error_view
    override val loadingViewLayoutId: Int = R.layout.loading_view
    override val emptyViewLayoutId: Int = R.layout.empty_view
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
