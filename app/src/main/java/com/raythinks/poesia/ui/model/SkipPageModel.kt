package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/10/27 0027<br>.
 * 版本：1.2.0
 */
data class SkipPageModel(var fromPage: Int, var skipPageNum: Int = 0) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(fromPage)
        writeInt(skipPageNum)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SkipPageModel> = object : Parcelable.Creator<SkipPageModel> {
            override fun createFromParcel(source: Parcel): SkipPageModel = SkipPageModel(source)
            override fun newArray(size: Int): Array<SkipPageModel?> = arrayOfNulls(size)
        }
    }
}