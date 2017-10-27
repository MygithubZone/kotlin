package com.raythinks.poesia.utils

import android.app.Activity
import android.content.Context
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.adapter.MenuTypeAdapter
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup
import kotlinx.android.synthetic.main.dialog_libros_bottommenu.view.*
import com.flyco.dialog.widget.MaterialDialog
import android.widget.AdapterView
import com.flyco.dialog.listener.OnOperItemClickL
import com.flyco.animation.ZoomExit.ZoomOutExit
import com.flyco.animation.ZoomEnter.ZoomInEnter
import com.flyco.dialog.widget.NormalListDialog
import com.flyco.dialog.entity.DialogMenuItem
import com.flyco.dialog.listener.OnBtnClickL
import android.text.TextUtils
import android.text.TextWatcher
import com.flyco.dialog.widget.ActionSheetDialog
import com.raythinks.poesia.ui.widget.EditDialog


/**
 * Created by 赵海 on 2017/10/6.
 */
object DialogUtils {
    fun initMenuDialog(context: Activity, theme: String, selectType: Int, array: Array<String>, lis: MenuTypeAdapter.OnMenuItemClickListener): BottomSheetDialog {
        var menuAdapter = MenuTypeAdapter(theme, selectType, array, lis)
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

    /**
     * @param context         上下文
     * @param title           标题
     * @param msg             提示信息
     * @param cancelBtn       取消
     * @param sureBtn         确认
     * @param onClickListener 点击事件
     * @return
     */
    fun showTwoBtnDialog(context: Context, title: String, msg: String, cancelBtn: String?, sureBtn: String?, onClickListener: DialogListener.DialogClickLisenter): MaterialDialog {
        return showTwoBtnDialog(context, true, title, msg, cancelBtn, sureBtn, onClickListener)
    }

    /**
     * @param context         上下文
     * @param title           标题
     * @param msg             提示信息
     * @param cancelBtn       取消
     * @param sureBtn         确认
     * @param onClickListener 点击事件
     * @return
     */
    fun showTwoBtnDialog(context: Context, cancelAble: Boolean, title: String, msg: String, cancelBtn: String?, sureBtn: String?, onClickListener: DialogListener.DialogClickLisenter): MaterialDialog {
        val dialog = MaterialDialog(context)
        dialog.setCancelable(cancelAble)
        val mBasIn = ZoomInEnter()
        val mBasOut = ZoomOutExit()
        val isTitle = !TextUtils.isEmpty(title)
        dialog.content(
                msg)//
                .title(if (isTitle) title else "")
                .isTitleShow(isTitle)
                .btnText(if (TextUtils.isEmpty(cancelBtn)) "取消" else cancelBtn, if (TextUtils.isEmpty(sureBtn)) "确定" else sureBtn)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show()
        dialog.setOnBtnClickL(
                OnBtnClickL //left btn click listener
                {
                    dialog.dismiss()
                    onClickListener.onDialogClick(CLICK_CANCEL)
                },
                OnBtnClickL //right btn click listener
                {
                    dialog.dismiss()
                    onClickListener.onDialogClick(CLICK_SURE)
                })
        return dialog
    }

    const val CLICK_CANCEL = 0
    const val CLICK_SURE = 1
    /**
     * 显示底部对话框
     *
     * @param mContext
     * @param title
     * @param stringItems
     * @return
     */
    fun showSheetDialog(mContext: Context, title: String, stringItems: Array<String>, dialogItemLisenter: DialogListener.DialogItemLisenter?): ActionSheetDialog {
        val dialog = ActionSheetDialog(mContext, stringItems, null)
        val isTitleShow = !TextUtils.isEmpty(title)
        dialog.isTitleShow(isTitleShow)
        if (isTitleShow)
            dialog.title(title)
        dialog.titleTextSize_SP(14.5f)//
                .show()
        dialog.setOnOperItemClickL { parent, view, position, id ->
            dialog.dismiss()
            if (dialogItemLisenter != null)
                dialogItemLisenter!!.onDialogClick(position)
        }
        return dialog
    }

    /**
     * 显示单个对话确认按钮
     *
     * @param context         上下文
     * @param cancelAble      是否可取消
     * @param title           是否显示标题
     * @param msg             内容提示
     * @param sureBtn         按钮名称
     * @param onClickListener 监听
     * @return
     */
    fun showOneBtnDialog(context: Context, cancelAble: Boolean, title: String, msg: String, sureBtn: String, onClickListener: DialogListener.DialogClickLisenter): MaterialDialog {
        val oneDialog = MaterialDialog(context)
        oneDialog.setCancelable(cancelAble)
        oneDialog.setCanceledOnTouchOutside(cancelAble)
        val mBasIn = ZoomInEnter()
        val mBasOut = ZoomOutExit()
        val isTitle = !TextUtils.isEmpty(title)
        oneDialog.content(
                msg)//
                .btnNum(1)
                .title(if (isTitle) title else "")
                .isTitleShow(isTitle)
                .btnText(if (TextUtils.isEmpty(sureBtn)) "确定" else sureBtn)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show()
        oneDialog.setOnBtnClickL(
                OnBtnClickL //left btn click listener
                {
                    oneDialog.dismiss()
                    onClickListener.onDialogClick(CLICK_SURE)
                })
        return oneDialog
    }

    fun showEidtDialog(context: Context, cancelAble: Boolean, title: String, msg: String = "", hint: String = "", sureBtn: String = "确定", cancelBtn: String = "取消", onClickListener: EditDialog.onDlialogClickLisenter, textWacher: TextWatcher): EditDialog {
        val oneDialog = EditDialog(context)
        oneDialog.setCancelable(cancelAble)
        oneDialog.setCanceledOnTouchOutside(cancelAble)
        val mBasIn = ZoomInEnter()
        val mBasOut = ZoomOutExit()
        oneDialog.titleText = title
        oneDialog.msg = msg
        oneDialog.hint = hint
        oneDialog.sureBtnText = sureBtn
        oneDialog.cancelBtnText = cancelBtn
        oneDialog.click = onClickListener
        oneDialog.inputLis = textWacher
        oneDialog
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show()
        return oneDialog
    }

    /**
     * 列表单选对话框
     *
     * @param mContext
     * @param title              标题
     * @param mMenuItems         列表
     * @param dialogItemLisenter 监听器
     */

    fun showListDialog(mContext: Context, title: String, mMenuItems: ArrayList<DialogMenuItem>, dialogItemLisenter: DialogListener.DialogItemLisenter) {
        val dialog = NormalListDialog(mContext, mMenuItems)
        val mBasIn = ZoomInEnter()
        val mBasOut = ZoomOutExit()
        dialog.title(title)//
                .titleTextSize_SP(16f)//
                .titleBgColor(ContextCompat.getColor(mContext, R.color.colorBackground))//
                .titleTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                .itemPressColor(ContextCompat.getColor(mContext, R.color.colorBackground))//
                .itemTextColor(ContextCompat.getColor(mContext, R.color.black_mid))//
                .itemTextSize(14f)//
                .cornerRadius(4f)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .dividerColor(ContextCompat.getColor(mContext, R.color.colorBackground))
                .widthScale(0.8f)//
                .heightScale(0.7f)
        //        Window dialogWindow = dialog.getWindow();
        //        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //        if (lp != null) {
        //            lp.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        //            lp.gravity = Gravity.CENTER;
        //        }
        //        dialogWindow.setAttributes(lp);
        dialog.show()
        dialog.setOnOperItemClickL { parent, view, position, id ->
            dialogItemLisenter.onDialogClick(position)
            dialog.dismiss()
        }
    }

    /**
     * 显示对话框
     *
     * @param context         上下文
     * @param title           标题
     * @param msg             提示信息
     * @param onClickListener 点击事件
     * @return
     */
    fun showTwoBtnDialog(context: Context, title: String, msg: String, onClickListener: DialogListener.DialogClickLisenter): MaterialDialog {
        return showTwoBtnDialog(context, title, msg, null, null, onClickListener)
    }
}

class DialogListener {
    class DialogClickLisenter {
        open fun onDialogClick(clicK_SURE: Int) {

        }
    }

    class DialogItemLisenter {
        open fun onDialogClick(position: Int) {}

    }

}
