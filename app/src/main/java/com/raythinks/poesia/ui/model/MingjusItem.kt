package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * 古文名句列表
 */
data class MingjusItem(val ipStr: String? = null,
                       val nameStr: String = "",
                       val author: String = "",
                       val exing: Int = 0,
                       val shiID: Int = 0,
                       val id: Int = 0,
                       val classStr: String? = null,
                       val type: String? = null,
                       val shiName: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(ipStr)
        writeString(nameStr)
        writeString(author)
        writeInt(exing)
        writeInt(shiID)
        writeInt(id)
        writeString(classStr)
        writeString(type)
        writeString(shiName)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MingjusItem> = object : Parcelable.Creator<MingjusItem> {
            override fun createFromParcel(source: Parcel): MingjusItem = MingjusItem(source)
            override fun newArray(size: Int): Array<MingjusItem?> = arrayOfNulls(size)
        }
    }
}