package com.raythinks.poesia.utils

import android.content.Context
import android.content.SharedPreferences

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Author:Shaojian
 * DATA:2016/11/15.
 * ACTION:SharedPreferences存储的简单封装
 * TYPE:工具类
 */
object SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    val FILE_NAME = "agent_preference"

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法

     * @param context
     * *
     * @param key
     * *
     * @param object
     */
    fun put(context: Context, key: String, `object`: Any, fileName: String = FILE_NAME) {

        val sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE)
        val editor = sp.edit()

        if (`object` is String) {
            editor.putString(key, `object`)
        } else if (`object` is Int) {
            editor.putInt(key, `object`)
        } else if (`object` is Boolean) {
            editor.putBoolean(key, `object`)
        } else if (`object` is Float) {
            editor.putFloat(key, `object`)
        } else if (`object` is Long) {
            editor.putLong(key, `object`)
        } else {
            var value: String? = `object`.toString()
            if (value == null) {
                value = ""
            }
            editor.putString(key, value)
        }

        editor.apply()
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值

     * @param context
     * *
     * @param key
     * *
     * @param defaultObject
     * *
     * @return
     */
    operator fun get(context: Context, key: String, defaultObject: Any, fileName: String = FILE_NAME): Any? {
        val sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE)
        if (defaultObject is String) {
            return sp.getString(key, defaultObject)
        } else if (defaultObject is Int) {
            return sp.getInt(key, defaultObject)
        } else if (defaultObject is Boolean) {
            return sp.getBoolean(key, defaultObject)
        } else if (defaultObject is Float) {
            return sp.getFloat(key, defaultObject)
        } else if (defaultObject is Long) {
            return sp.getLong(key, defaultObject)
        }
        return null
    }

    /**
     * 移除某个key值已经对应的值

     * @param context
     * *
     * @param key
     */
    fun remove(context: Context, key: String, fileName: String = FILE_NAME) {
        val sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }

    /**
     * 清除所有数据

     * @param context
     */
    fun clear(context: Context, fileName: String = FILE_NAME) {
        val sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

    /**
     * 查询某个key是否已经存在

     * @param context
     * *
     * @param key
     * *
     * @return
     */
    fun contains(context: Context, key: String, fileName: String = FILE_NAME): Boolean {
        val sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE)
        return sp.contains(key)
    }

    /**
     * 返回所有的键值对

     * @param context
     * *
     * @return
     */
    fun getAll(context: Context, fileName: String = FILE_NAME): Map<String, *> {
        val sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE)
        return sp.all
    }
}
