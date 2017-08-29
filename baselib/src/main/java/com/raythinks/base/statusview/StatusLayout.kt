package com.raythinks.base.statusview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.raythinks.base.R
import java.lang.IllegalStateException

/**
 * Created by Administrator on 2016/10/19.
 */

class StatusLayout : FrameLayout {
    private lateinit var mContext: Context
    private var mLoadingView: View? = null// 正在加载界面
    private var mErrorView: View? = null// 返回数据错误
    private var mContentView: View? = null// 正常的内容页面
    private var mEmptyView: View? = null// 返回数据是0个
    private var isInit = false
    private var layoutParams: FrameLayout.LayoutParams? = null
    private var statusView: StatusView? = null

    constructor(context: Context) : super(context) {
        this.mContext = context

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mContext = context
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        this.mContext = context
    }

    @SuppressLint("InflateParams")
    private fun initStatusLayout() {
        if (isInit) {
            return
        }
        isInit = true
        val countNum = childCount
        if (countNum != 1) {
            return
        }
        log(getChildAt(0).javaClass.getName())
        mContentView = getChildAt(0)
        mContentView!!.setTag(R.id.tag_empty_add_type, CONTENT)
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        setStatusView(DefaultStatusView(mContext))
    }

    fun setStatusView(mStatusView: StatusView): StatusLayout {
        this.statusView = mStatusView
        setLoadingView()
        setErrorView()
        setEmptyView()
        showContent()
        return this
    }

    /**
     * 设置加载页面

     * @return
     */
    fun setLoadingView(): StatusLayout {
        removeView(mLoadingView)
        mLoadingView = statusView!!.loadingView
        mLoadingView!!.setTag(R.id.tag_empty_add_view, true)
        mLoadingView!!.setTag(R.id.tag_empty_add_type, LOADING)
        addView(mLoadingView, layoutParams)
        mLoadingView!!.visibility = View.GONE
        return this

    }


    /**
     * 设置重试界面

     * @return
     */
    fun setErrorView(): StatusLayout {
        removeView(mErrorView)
        mErrorView = statusView!!.errorView
        mErrorView!!.setTag(R.id.tag_empty_add_view, true)
        mErrorView!!.setTag(R.id.tag_empty_add_type, ERROR)
        addView(mErrorView, layoutParams)
        mErrorView!!.visibility = View.GONE
        return this

    }


    /**
     * 设置空的页面

     * @return
     */
    fun setEmptyView(): StatusLayout {
        removeView(mEmptyView)
        mEmptyView = statusView!!.emptyView
        mEmptyView!!.setTag(R.id.tag_empty_add_view, true)
        mEmptyView!!.setTag(R.id.tag_empty_add_type, EMPTY)
        addView(mEmptyView, layoutParams)
        mEmptyView!!.visibility = View.GONE
        return this
    }


    override fun addView(child: View, index: Int,
                         params: ViewGroup.LayoutParams) {
        var isAddEmpty = false
        if (child.getTag(R.id.tag_empty_add_view) != null) {
            isAddEmpty = child.getTag(R.id.tag_empty_add_view) as Boolean
        }
        if (!isAddEmpty) {
            if (childCount > 0) {
                throw IllegalStateException(
                        "StatusLayout can host only one direct child")
            }
        }
        super.addView(child, index, params)
        initStatusLayout()
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
                    getChildAt(i).visibility = View.VISIBLE
                } else {
                    getChildAt(i).visibility = View.GONE
                }
            }
        }

    }

    class Builder {
        private var index: Int = 0
        private var mParentView: ViewGroup? = null
        private var mContentView: View? = null// 正常的内容页面
        private lateinit var statusLayout: StatusLayout
        private var isRegister = false
        private lateinit var statusView: StatusView

        fun setContentView(activity: Activity): Builder {
            mParentView = activity
                    .findViewById<View>(android.R.id.content) as ViewGroup
            index = 0
            return this
        }

        fun setContentView(fragment: Fragment): Builder {
            mParentView = fragment.view!!.parent as ViewGroup
            index = 0
            return this
        }

        fun setContentView(view: View): Builder {
            mParentView = view.parent as ViewGroup
            for (i in 0..mParentView!!.childCount - 1) {
                if (mParentView!!.getChildAt(i) === view) {
                    index = i
                    break
                }
            }
            return this
        }

        fun build(): StatusLayout {
            val view = mParentView!!.getChildAt(index)
            // 父类本分就是StatusLayout
            if (mParentView != null && mParentView is StatusLayout) {
                statusLayout = mParentView as StatusLayout
            } else if (view != null && view is StatusLayout) {
                statusLayout = view
            } else {
                mContentView = view
                statusLayout = StatusLayout(
                        mParentView!!.context)
                register()

            }// 其他情况
            // 内容本分就是StatusLayout
            if (statusView != null) {
                statusLayout!!.setStatusView(statusView)
            }
            return statusLayout
        }


        fun setStatusView(statusView: StatusView): Builder {
            this.statusView = statusView
            return this
        }

        /**
         * @描述：只需要注册一次
         */
        fun register() {
            Log.d(TAG, "register: 添加布局")
            if (isRegister) {
                return
            }
            if (mParentView == null || mContentView == null || statusLayout == null) {
                return
            }
            mParentView!!.removeView(mContentView)
            val lp = mContentView!!.layoutParams
            statusLayout!!.addView(mContentView)
            mParentView!!.addView(statusLayout, index, lp)
            isRegister = true
        }

        /**
         * @描述：无所谓的调用
         */
        //        public void unRegister() {
        //            if (isRegister) {
        //                if (mParentView == null || mContentView == null
        //                        || statusLayout == null) {
        //                    return;
        //                }
        //                mParentView.setTag(R.id.tag_empty_layout_manager, null);
        //                mParentView.removeView(statusLayout);
        //                ViewGroup.LayoutParams lp = statusLayout.getLayoutParams();
        //                mParentView.addView(mContentView, index, lp);
        //                mContentView.setVisibility(View.VISIBLE);
        //            }
        //
        //        }
    }

    companion object {

        private val TAG = "StatusLayout"
        private val LOADING = 1
        private val ERROR = 2
        private val CONTENT = 3
        private val EMPTY = 4

        fun getInstance(view: View, statusView: StatusView): StatusLayout {
            return Builder().setContentView(view).setStatusView(statusView).build()
        }

        fun getInstance(activity: Activity, statusView: StatusView): StatusLayout {
            return Builder().setContentView(activity).setStatusView(statusView).build()
        }

        fun getInstance(fragment: Fragment, statusView: StatusView): StatusLayout {
            return Builder().setContentView(fragment).setStatusView(statusView).build()
        }
    }
}
