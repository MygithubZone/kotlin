package com.raythinks.poesia.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.flyco.dialog.widget.MaterialDialog
import com.flyco.dialog.widget.base.BaseDialog
import com.flyco.animation.Attention.Swing
import com.flyco.animation.ZoomEnter.ZoomInEnter
import com.flyco.animation.ZoomExit.ZoomOutExit
import com.raythinks.poesia.R
import kotlinx.android.synthetic.main.dialog_edit_style1.view.*


/**
 * Created by zh on 2017/10/26.
 */
class EditDialog : BaseDialog<EditDialog> {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: Boolean) : super(context, attrs) {
    }

    var titleText = "提示";
    var msg="您要跳转到"
    var hint="0-15的数字"
    var sureBtnText="确定"
    var cancelBtnText="取消"
    override fun setUiBeforShow() {
        mOnCreateView.tv_dialog_title.text=titleText
        mOnCreateView.tv_dialog_msg.text=msg
        mOnCreateView.et_dialog_content.hint=hint
        mOnCreateView.tv_dialog_sure.text=sureBtnText
        mOnCreateView.tv_dialog_cancel.text=cancelBtnText
        mOnCreateView.tv_dialog_cancel.setOnClickListener {
            dismiss()
        }
        mOnCreateView.tv_dialog_sure.setOnClickListener {
            dismiss()
        }
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