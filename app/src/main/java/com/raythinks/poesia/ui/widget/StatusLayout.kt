package com.raythinks.poesia.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.raythinks.poesia.R
import com.raythinks.poesia.net.ERROR_MEG_DATANULL
import com.raythinks.poesia.net.ERROR_MEG_NET
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.error_view.view.*

/**
 * Created by Administrator on 2016/10/19.
 */

class StatusLayout : FrameLayout {
    private var mContext: Context
    lateinit var mErrorView: View// 返回数据错误
    lateinit var mLoadingView: View// 正在加载界面
    lateinit var mEmptyView: View// 返回数据是0个
    private var mContentView: View? = null// 正常的内容页面
    private var layoutParams: FrameLayout.LayoutParams? = null
    fun showEmpty(msg: String = ERROR_MEG_DATANULL, initdata: () -> Unit) {
        mEmptyView.tv_empty.text = msg
        mEmptyView.btn_empty_retry.setOnClickListener {
            initdata()
        }
        showEmpty()
    }

    fun showError(msg: String? = ERROR_MEG_NET, initdata: () -> Unit) {
        mErrorView.tv_error.text = msg
        mErrorView.btn_error_retry.setOnClickListener {
            initdata()
        }
        showError()
    }

    constructor(context: Context) : super(context) {
        this.mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mContext = context
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        this.mContext = context
    }

    private fun setStatusView(): StatusLayout {
        if (childCount != 1) {
            throw Exception("this view only contains one")
        }
        var mInflater = LayoutInflater.from(mContext)
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        mContentView = getChildAt(0)
        mContentView?.setTag(R.id.tag_empty_add_type, CONTENT)
        mLoadingView = mInflater.inflate(R.layout.loading_view, this, false)
        mErrorView = mInflater.inflate(R.layout.error_view, this, false)
        mEmptyView = mInflater.inflate(R.layout.empty_view, this, false)
        initView(mLoadingView, LOADING)
        initView(mErrorView, ERROR)
        initView(mEmptyView, EMPTY)
        showContent()
        return this
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setStatusView()
    }

    /**
     * 设置重试界面

     * @return
     */
    fun initView(view: View, type: Int): StatusLayout {
        view!!.setTag(R.id.tag_empty_add_type, type)
        addView(view, layoutParams)
        view!!.visibility = View.GONE
        return this

    }


    private fun log(string: String) {
        Log.e(TAG, string)
    }

    fun showLoading() {
        log("showLoading")
        show(LOADING)

    }

    fun showError() {
        log("showError")
        show(ERROR)
    }

    fun showContent() {
        log("showContent")
        show(CONTENT)
    }

    fun showEmpty() {
        log("showEmpty")
        show(EMPTY)
    }


    private fun show(type: Int) {
        for (i in 0..childCount - 1) {
            val mType: Int
            if (getChildAt(i).getTag(R.id.tag_empty_add_type) != null) {
                mType = getChildAt(i).getTag(R.id.tag_empty_add_type) as Int
                if (type == mType) {
                    Log.e("aaashow", "${i}${getChildAt(i).getTag(R.id.tag_empty_add_type)}")
                    getChildAt(i).visibility = View.VISIBLE
                } else {
                    Log.e("aaahide", "${i}${getChildAt(i).getTag(R.id.tag_empty_add_type)}")
                    getChildAt(i).visibility = View.GONE
                }
            }
        }

    }

    private val TAG = "StatusLayout"
    private val LOADING = 1
    private val ERROR = 2
    private val CONTENT = 3
    private val EMPTY = 4

}
