package com.raythinks.poesia.base

/**
 * 功能：网络异常类<br>
 * 作者：zh<br>
 * 时间： 2017/10/18 0018<br>.
 * 版本：1.2.0
 */
/**
 * 数据为空
 */
const val ERROR_STATUS_THROWABLE = -1//
/**
 * 数据为空
 */
const val ERROR_STATUS_DATANULL = -2
const val ERROR_MEG_NET = "网络异常，请稍后重试"
const val ERROR_MEG_DATANULL = "亲，暂无任何数据额~"

data class NetError(var status: Int = ERROR_STATUS_THROWABLE, var msg: String? = ERROR_MEG_NET, var fromApi: String?, var error: Throwable?,var extraData:String="")