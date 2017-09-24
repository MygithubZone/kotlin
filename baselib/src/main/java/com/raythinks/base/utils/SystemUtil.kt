package com.raythinks.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import com.raythinks.base.BaseApp
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/19 0019<br>.
 * 版本：1.2.0
 */
object SystemUtil {
    val PATH_DATA = BaseApp.mContext.cacheDir.absolutePath + File.separator + "data"

    val PATH_CACHE = PATH_DATA + "/NetCache"

    /**
     * 检查WIFI是否连接
     */
    fun isWifiConnected(): Boolean {
        val connectivityManager = BaseApp.mContext.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return wifiInfo != null
    }

    /**
     * 检查手机网络(4G/3G/2G)是否连接
     */
    fun isMobileNetworkConnected(): Boolean {
        val connectivityManager = BaseApp.mContext.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return mobileNetworkInfo != null
    }

    /**
     * 检查是否有可用网络
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager = BaseApp.mContext.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dp2px(dpValue: Float): Int {
        val scale = BaseApp.mContext.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun px2dp(pxValue: Float): Int {
        val scale = BaseApp.mContext.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取进程号对应的进程名

     * @param pid 进程号
     * *
     * @return 进程名
     */
    fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                if (reader != null) {
                    reader.close()
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

        }
        return null
    }
}