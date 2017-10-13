package com.raythinks.poesia.listener

import com.raythinks.poesia.ui.widget.SelectableTextView
import java.util.ArrayList

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/10/13 0013<br>.
 * 版本：1.2.0
 */
class CopyActionCallBack : SelectableTextView.CustomActionMenuCallBack {
    override fun onCreateCustomActionMenu(menu: SelectableTextView.ActionMenu?): Boolean {
        menu!!.setActionMenuBgColor(0xbb000000.toInt())
        menu!!.setMenuItemTextColor(0xffffffff.toInt())
        val titleList = ArrayList<String>()
        titleList.add("复制")
        titleList.add("翻译")
        titleList.add("分享")
        menu.addCustomMenuItem(titleList)
        return true
    }

    override fun onCustomActionItemClicked(itemTitle: String?, selectedContent: String?) {
    }

}