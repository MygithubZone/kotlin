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
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.net.ApiRefranesList
import com.raythinks.poesia.ui.adapter.RefranesAdapter
import com.raythinks.poesia.ui.adapter.MenuTypeAdapter
import com.raythinks.poesia.ui.viewmodel.RefranesViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.DialogUtils
import com.raythinks.poesia.utils.TUtils
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup
import kotlinx.android.synthetic.main.dialog_libros_bottommenu.view.*
import kotlinx.android.synthetic.main.fragment_refranes.*
import java.util.ArrayList


/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class RefranesFragment : BaseVMFragment<RefranesViewModel>(), MenuTypeAdapter.OnMenuItemClickListener {
    override fun onItemClick(ad: MenuTypeAdapter, selection: Int, position: Int, itemView: View) {
        var temp = ad!!.itemArray[selection][position]
        if (!TextUtils.equals(type, temp) || !TextUtils.equals(theme, temp)) {
            if (ad!!.selectType == 1) {
                if ("不限".equals(temp)) {
                    theme = ""
                    tv_refranes_theme.text = "主题"
                } else {
                    this.theme = temp
                    tv_refranes_theme.text = theme
                }
                type = ""
                tv_refranes_type.text = "分类"
                setTypeMenu()
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
        if (ad!!.selectType == 1) {
            mThemeSheetDialog?.dismiss()
        } else {
            mTypeSheetDialog?.dismiss()
        }

    }

    lateinit var adapter: RefranesAdapter
    override fun initView() {
        AnimUtils.loadAmin(_mActivity, cl_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = RefranesAdapter(viewModel)
        recyclerview.setAdapter(adapter)
        tv_refranes_theme.setOnClickListener {
            setThemeMenu()
            mThemeSheetDialog?.show()
        }
        tv_refranes_type.setOnClickListener {
            if (TextUtils.isEmpty(theme)) {
                TUtils.showToast(toastStr = "请先选择主题", gravity = Gravity.CENTER)
                return@setOnClickListener
            }
            setTypeMenu()
            mTypeSheetDialog?.show()
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

    private fun setTypeMenu() {
        typeAdapter?.updateData(theme, 0, typeArray[themeArray.indexOf(theme) - 1])
    }

    private fun setThemeMenu() {
        var tempTheme = theme
        if (TextUtils.isEmpty(tempTheme)) {
            tempTheme = "主题"
        }
        themeAdapter?.updateData(tempTheme, 1, themeArray)
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

    var mThemeSheetDialog: BottomSheetDialog? = null
    var mTypeSheetDialog: BottomSheetDialog? = null
    var themeAdapter: MenuTypeAdapter? = null
    var typeAdapter: MenuTypeAdapter? = null
    fun initMenu() {
        themeAdapter = MenuTypeAdapter(this)
        typeAdapter = MenuTypeAdapter(this)
        setThemeMenu()
        typeAdapter?.updateData(theme, 0, typeArray[0])
        mThemeSheetDialog = DialogUtils.initMenuDialog(_mActivity, themeAdapter!!)
        mTypeSheetDialog = DialogUtils.initMenuDialog(_mActivity, typeAdapter!!)
    }

    override fun getLayoutId(): Int = R.layout.fragment_refranes
    var typeArray = ArrayList<Array<String>>()
    var themeArray: Array<String>

    init {
        themeArray = arrayOf("不限", "抒情", "四季", "山水", "天气", "人物", "人生", "生活", "节日", "动物", "植物", "食物")
        typeArray.add(arrayOf("不限", "爱情", "友情", "离别", "思念", "思乡", "伤感", "孤独", "闺怨", "悼亡", "怀古", "爱国", "感恩"))
        typeArray.add(arrayOf("不限", "春天", "夏天", "秋天", "冬天"))
        typeArray.add(arrayOf("不限", "庐山", "泰山", "江河", "长江", "黄河", "西湖", "瀑布"))
        typeArray.add(arrayOf("不限", "写风", "写云", "写雨", "写雪", "彩虹", "太阳", "月亮", "星星"))
        typeArray.add(arrayOf("不限", "女子", "父亲", "母亲", "老师", "儿童"))
        typeArray.add(arrayOf("不限", "励志", "哲理", "青春", "时光", "梦想", "读书", "战争"))
        typeArray.add(arrayOf("不限", "乡村", "田园", "边塞", "写桥"))
        typeArray.add(arrayOf("不限", "春节", "元宵节", "寒食节", "清明节", "端午节", "七夕节", "中秋节", "重阳节"))
        typeArray.add(arrayOf("不限", "写鸟", "写马", "写猫"))
        typeArray.add(arrayOf("不限", "梅花", "梨花", "桃花", "荷花", "菊花", "柳树", "叶子", "竹子"))
        typeArray.add(arrayOf("不限", "写酒", "写茶", "荔枝"))
    }
}