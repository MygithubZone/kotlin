package com.raythinks.poesia.utils

import android.app.Activity
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.adapter.MenuTypeAdapter
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup
import kotlinx.android.synthetic.main.dialog_libros_bottommenu.view.*


/**
 * Created by 赵海 on 2017/10/6.
 */
object DialogUtils {
    fun initMenuDialog(context: Activity, theme: String, selectType: Int, array: Array<String>,lis: MenuTypeAdapter.OnMenuItemClickListener): BottomSheetDialog {
        var menuAdapter=MenuTypeAdapter(theme,selectType,array,lis)
        var mBottomSheetDialog = BottomSheetDialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_libros_bottommenu, null)
        view.recyclerview_libros_menu.adapter = menuAdapter
        var gridManager = GridLayoutManager(context, 3)
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
        var bottomSheetBehavior = BottomSheetBehavior.from(parent)
        view.iv_dialog_paddingtop.setOnClickListener { mBottomSheetDialog?.dismiss() }
        mBottomSheetDialog.setOnDismissListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        mBottomSheetDialog.show()
        return mBottomSheetDialog
    }
}