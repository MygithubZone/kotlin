package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.net.ApiLibrosList
import com.raythinks.poesia.ui.adapter.LibrosAdapter
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.fragment_libros.*
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.widget.FrameLayout
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.ui.adapter.LibrosTypeAdapter
import com.raythinks.poesia.utils.ActivityRouterUtils
import com.raythinks.shiwen.viewmodel.MainViewModel
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup
import kotlinx.android.synthetic.main.dialog_libros_bottommenu.view.*


/**
 * Created by zh on 2017/9/20.
 */
class LibrosFragment : BaseVMFragment<MainViewModel>(), TabLayout.OnTabSelectedListener, OnItemClickListener, OnSelectionItemClickListener {
    override fun onItemClick(selection: Int, position: Int, itemView: View) {
        type = menuAdapter?.headerAarry!![selection][position]
        if (selection != tbs_libros_type?.selectedTabPosition) {
            tbs_libros_type?.getTabAt(selection)?.select()
        }
        resetTypeUpdateData()
        mBottomSheetDialog?.dismiss()
    }

    override fun onItemClick(position: Int, itemView: View) {
        ActivityRouterUtils.startLibrosDetailActivity(_mActivity, adapter.data[position].id, adapter.data[position].nameStr)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (TextUtils.isEmpty(type) || !menuAdapter?.headerAarry!![tab?.position ?: 0]?.contains(type)) {
            var position = tab?.position
            if (position == null) {
                type = ""
            } else {
                type = libros_type_Strs[position]
            }
            resetTypeUpdateData()
        }
        updatePage()

    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        updatePage()
    }

    private fun updatePage() {
        var tempType = ""
        if (!libros_type_Strs.contains(type)) {
            tempType = type
        }
        viewModel.updatePage(3, currentP, sumP, 0, tempType)
    }

    private fun resetTypeUpdateData() {
        adapter.clearData()
        currentP = 1
        sumP = 1
        stl.showLoading()
        viewModel.updateLibrosList(currentP, type)
    }

    lateinit var adapter: LibrosAdapter

    lateinit var libros_type_Strs: Array<String>
    override fun initView() {
        AnimUtils.loadAmin(_mActivity, ll_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = LibrosAdapter(viewModel, this)
        recyclerview.setAdapter(adapter)
        libros_type_Strs = resources.getStringArray(R.array.arrayt_libros_type)
        TUtils.setTab(_mActivity, libros_type_Strs, tbs_libros_type)
        tbs_libros_type.addOnTabSelectedListener(this)
        refreshLayout.setOnRefreshListener {
            isInitRefresh = true
            viewModel.updateLibrosList(1, type)
        }
        refreshLayout.setOnLoadmoreListener {
            isInitRefresh = false
            viewModel.updateLibrosList(currentP + 1, type)
        }
        iv_libros_more.setOnClickListener {
            mBottomSheetDialog?.show()
        }
        initMenu()
        viewModel.getSkip().observe(this, Observer {
            if (it?.fromPage==3){
                if(it?.skipPageNum==-1){
                    if (!refreshLayout.isRefreshing&&!refreshLayout.isLoading)
                    recyclerview.smoothScrollToPosition(0);
                }else{
                isInitRefresh=true
                viewModel.updateLibrosList(it?.skipPageNum,  type)
                }
            }
        })
    }

    var type = ""
    var mBottomSheetDialog: BottomSheetDialog? = null
    var menuAdapter: LibrosTypeAdapter? = null
    fun initMenu() {
        mBottomSheetDialog = BottomSheetDialog(_mActivity)
        val view = layoutInflater.inflate(R.layout.dialog_libros_bottommenu, null)
        menuAdapter = LibrosTypeAdapter(_mActivity, this)
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

    var currentP: Int = 1
    var sumP: Int = 1
    override fun initData() {
        stl.showLoading()
        viewModel.updateLibrosList(currentP, type).observe(this, Observer {
            var books = it?.books
            currentP = it?.currentPage ?: 1
            sumP = it?.sumPage ?: 1
            if (books == null || books.size == 0) {//返回列表为空
                if (it?.currentPage == 1) {//返回列表为空,且第一页
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
            } else {//返回列表不为空
                if (it?.currentPage == 1) {//第一页
                    stl.showContent()
                }
                refreshLayout.setEnableLoadmore(currentP < sumP)
                adapter.updateData(isInitRefresh, books)
            }
            if (isVisible)
            updatePage()
            refreshLayout.finishRefershOrLoadMore(currentP == 1)
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiLibrosList -> {
                    updatePage()
                    if (currentP == 1) {
                        stl.showError(it.msg, { initData() })
                    }
                    refreshLayout.finishRefershOrLoadMore(currentP == 1)
                    return@Observer
                }

            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_libros
}