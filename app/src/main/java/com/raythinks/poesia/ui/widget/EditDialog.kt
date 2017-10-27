package com.raythinks.poesia.ui.widget

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import com.flyco.animation.ZoomEnter.ZoomInEnter
import com.flyco.animation.ZoomExit.ZoomOutExit
import com.flyco.dialog.widget.base.BaseDialog
import com.raythinks.poesia.R
import kotlinx.android.synthetic.main.dialog_edit_style1.view.*


/**
 * Created by zh on 2017/10/26.
 */
class EditDialog : BaseDialog<EditDialog> {
    interface onDlialogClickLisenter {
        fun onClickEidtDialog(dialog: EditDialog, type: Int, content: String)
    }

    constructor(context: Context) : super(context) {
    }


    var titleText = "提示";
    var msg = "您要跳转到"
    var hint = "0-15的数字"
    var sureBtnText = "确定"
    var cancelBtnText = "取消"
    var click: onDlialogClickLisenter? = null
    var inputLis: TextWatcher? = null
    var til_dialog_content: TextInputLayout? = null
    override fun setUiBeforShow() {
        mOnCreateView.tv_dialog_title.text = titleText
        mOnCreateView.tv_dialog_msg.text = msg
        til_dialog_content = mOnCreateView.til_dialog_content
        mOnCreateView.til_dialog_content.hint = hint
        mOnCreateView.et_dialog_content.addTextChangedListener(inputLis)
        mOnCreateView.tv_dialog_sure.text = sureBtnText
        mOnCreateView.tv_dialog_cancel.text = cancelBtnText
        mOnCreateView.tv_dialog_sure.setOnClickListener {
            click?.onClickEidtDialog(this, 1, mOnCreateView.et_dialog_content.text.toString())
        }
        mOnCreateView.tv_dialog_cancel.setOnClickListener {
            click?.onClickEidtDialog(this, 0, mOnCreateView.et_dialog_content.text.toString())
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    override fun onCreateView(): View {
        widthScale(0.85f)
        val mBasIn = ZoomInEnter()
        showAnim(mBasIn)
        dismissAnim(ZoomOutExit());
        val inflate = View.inflate(context, R.layout.dialog_edit_style1, null)
        return inflate
    }
}