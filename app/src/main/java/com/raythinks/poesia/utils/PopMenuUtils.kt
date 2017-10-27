package com.raythinks.poesia.utils

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by zh on 2017/10/6.
 */
object PopMenuUtils {
    fun showPopMenu(context: Context, moreMenu: View, menuList: Array<String>, onMenuListener: PopupMenu.OnMenuItemClickListener) {
        showPopMenu(context, moreMenu, menuList.toList(), onMenuListener)
    }

    fun showPopMenu(context: Context, moreMenu: View, menuList: List<String>, onMenuListener: PopupMenu.OnMenuItemClickListener) {
        val popup = PopupMenu(context, moreMenu)
        //Inflating the Popup using xml file
        for (str in menuList)
            popup.getMenu().add(str)
        popup.gravity=Gravity.CENTER_HORIZONTAL
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(onMenuListener)
        popup.show() //showing popup menu
    }
}