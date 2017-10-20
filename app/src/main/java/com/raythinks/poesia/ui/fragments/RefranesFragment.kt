package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.net.ApiRefranesList
import com.raythinks.poesia.ui.adapter.LibrosTypeAdapter
import com.raythinks.poesia.ui.adapter.RefranesAdapter
import com.raythinks.poesia.ui.adapter.RefranesTypeAdapter
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup
import kotlinx.android.synthetic.main.dialog_libros_bottommenu.view.*
import kotlinx.android.synthetic.main.fragment_refranes.*


/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class RefranesFragment : BaseVMFragment<RefranesViewModel>(), OnSelectionItemClickListener {
    override fun onItemClick(selection: Int, position: Int, itemView: View) {
        var temp = menuAdapter!!.itemArray[selection][position]
        if (!TextUtils.equals(type, temp) || !TextUtils.equals(theme, temp)) {
            if (menuAdapter!!.isTheme) {
                if ("不限".equals(temp)) {
                    theme = ""
                    tv_refranes_theme.text = "主题"
                } else {
                    this.theme = temp
                    tv_refranes_theme.text = theme
                }
                type = ""
                tv_refranes_type.text = "分类"
            } else {
                if ("不限".equals(temp)) {
                    type = ""
                    tv_refranes_type.text = "分类"
                } else {
                    type = temp
                    tv_refranes_type.text = type
                }
            }
            stl.showLoading()
            adapter.clearData()
            viewModel.updateRefranesList(1, theme, type)
        }
        mBottomSheetDialog?.dismiss()
    }

    lateinit var adapter: RefranesAdapter
    override fun initView() {
        AnimUtils.loadAmin(_mActivity, cl_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = RefranesAdapter(viewModel)
        recyclerview.setAdapter(adapter)
        tv_refranes_theme.setOnClickListener {
            var tempTheme = theme
            if (TextUtils.isEmpty(tempTheme)) {
                tempTheme = "主题"
            }
            menuAdapter?.updateData(tempTheme, type, true)
            mBottomSheetDialog?.show()
        }
        tv_refranes_type.setOnClickListener {
            if (TextUtils.isEmpty(theme)) {
                TUtils.showToast(toastStr = "请先选择主题", gravity = Gravity.CENTER)
                return@setOnClickListener
            }
            menuAdapter?.updateData(theme, type, false)
            mBottomSheetDialog?.show()
        }
        refreshLayout.setOnRefreshListener {
            isInitRefresh = true
            viewModel.updateRefranesList(1, theme, type)
        }
        refreshLayout.setOnLoadmoreListener {
            isInitRefresh = false
            viewModel.updateRefranesList(currentP + 1, theme, type)

        }
        initMenu()

    }

    var theme = ""
    var type = ""
    var currentP: Int = 1
    var sumP: Int = 1
    override fun initData() {
        stl.showLoading()
        viewModel.updateRefranesList(1, theme, type).observe(this, Observer {
            var mingjus = it?.mingjus
            currentP = it?.currentPage ?: 1
            sumP = it?.sumPage ?: 1
            if (mingjus == null || mingjus.size == 0) {
                if (it?.currentPage == 1) {
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
            } else {
                if (it?.currentPage == 1) {
                    stl.showContent()
                }
                refreshLayout.setEnableLoadmore(currentP < sumP)
                adapter.updateData(isInitRefresh, mingjus)
            }
            refreshLayout.finishRefershOrLoadMore(currentP == 1)
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiRefranesList -> {
                    TUtils.showToast(it?.msg ?: "aaaa")
                    if (currentP == 1) {
                        stl.showError(it.msg, { initData() })
                    }
                    refreshLayout.finishRefershOrLoadMore(currentP == 1)
                    return@Observer
                }
            }
        })
    }

    var mBottomSheetDialog: BottomSheetDialog? = null
    var menuAdapter: RefranesTypeAdapter? = null
    fun initMenu() {
        mBottomSheetDialog = BottomSheetDialog(_mActivity)
        val view = layoutInflater.inflate(R.layout.dialog_libros_bottommenu, null)
        menuAdapter = RefranesTypeAdapter(_mActivity, this)
        view.recyclerview_libros_menu.adapter = menuAdapter
        var gridManager = GridLayoutManager(_mActivity, 3)
        gridManager.spanSizeLookup = SectionedSpanSizeLookup(menuAdapter, gridManager);
        view.recyclerview_libros_menu.setLayoutManager(gridManager)
        mBottomSheetDialog?.setContentView(view)
        var bgView = mBottomSheetDialog?.window?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        bgView?.setBackgroundResource(android.R.color.transparent);
        var parent = view.getParent() as View
        var behavior = BottomSheetBehavior.from(parent);
        view.measure(0, 0);
        behavior.setPeekHeight(view.getMeasuredHeight());
        var params = parent.getLayoutParams() as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP
        parent.setLayoutParams(params);
        view.iv_dialog_paddingtop.setOnClickListener { mBottomSheetDialog?.dismiss() }
        mBottomSheetDialog?.setOnDismissListener {

        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_refranes
}