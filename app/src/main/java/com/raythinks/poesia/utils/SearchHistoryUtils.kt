package com.raythinks.poesia.utils

import android.content.Context
import android.text.TextUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/10/25 0025<br>.
 * 版本：1.2.0
 */
object SearchHistoryUtils {
    fun initData(context: Context): ArrayList<String> {
        var longHistory = SPUtils.get(context, "SEARCH_HISTORY", "") as String;
        var tmpHistory = longHistory.split(",");                            //split后长度为1有一个空串对象
        var historyList = ArrayList<String>(tmpHistory);
        historyList = ArrayList<String>(historyList.filterNot { TextUtils.isEmpty(it) })
        return historyList
    }

    /**
     * 保存搜索记录
     *
     * @param inputText 输入的历史记录
     */
    fun saveSearchHistory(context: Context, inputText: String) {

        if (TextUtils.isEmpty(inputText)) {
            return;
        }

        var longHistory = SPUtils.get(context, "SEARCH_HISTORY", "") as String;        //获取之前保存的历史记录
        var tmpHistory = longHistory.split(",");                            //逗号截取 保存在数组中
        var historyList = ArrayList<String>(tmpHistory) //数组转换成ArrayList
        if (!historyList.isEmpty()) {
            historyList = ArrayList<String>(historyList.filterNot { TextUtils.equals(it, inputText) })
            historyList.add(0, inputText);                           //将新输入的文字添加集合的第0位也就是最前面
            if (historyList.size > 20) {
                historyList.removeAt(historyList.size - 1);         //最多保存10条搜索记录 删除最早搜索的那一项
            }
            //逗号拼接
            var sb = historyList.joinToString(",")
            //保存到sp
            SPUtils.put(context, "SEARCH_HISTORY", sb.toString());
        } else {
            //之前未添加过
            SPUtils.put(context, "SEARCH_HISTORY", inputText + ",");
        }
    }

    fun clear(context: Context) {
        SPUtils.remove(context, "SEARCH_HISTORY");                 //清空sp
    }
}