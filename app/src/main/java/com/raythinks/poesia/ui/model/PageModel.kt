package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/10/27 0027<br>.
 * 版本：1.2.0
 */
data class PageModel(var fromPage: Int, var currP: Int = 0, var sumP: Int = 0, var sumCount: Int = 0, var typeKey: String = "", var type: String = "", var theme: String = "", var chao: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(fromPage)
        writeInt(currP)
        writeInt(sumP)
        writeInt(sumCount)
        writeString(typeKey)
        writeString(type)
        writeString(theme)
        writeString(chao)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PageModel> = object : Parcelable.Creator<PageModel> {
            override fun createFromParcel(source: Parcel): PageModel = PageModel(source)
            override fun newArray(size: Int): Array<PageModel?> = arrayOfNulls(size)
        }
    }
}