package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable

data class GushiwensItem(val langsongAuthor: String = "",
                         val nameStr: String = "",
                         val axing: Int = 0,
                         val author: String = "",
                         val cxing: Int = 0,
                         val yizhuCankao: String = "",
                         val exing: Int = 0,
                         val type: String? = null,
                         val yizhu: String = "",
                         val yizhuIspass: Boolean = false,
                         val bxing: Int = 0,
                         val chaodai: String = "",
                         val langsongAuthorPY: String = "",
                         val dxing: Int = 0,
                         val yizhuYuanchuang: Boolean = false,
                         val yizhuAuthor: String = "",
                         val id: Int = 0,
                         val tag: String = "",
                         val cont: String = "",
                         val shangIspass: Boolean = false) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readInt(),
            1 == source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(langsongAuthor)
        writeString(nameStr)
        writeInt(axing)
        writeString(author)
        writeInt(cxing)
        writeString(yizhuCankao)
        writeInt(exing)
        writeString(type)
        writeString(yizhu)
        writeInt((if (yizhuIspass) 1 else 0))
        writeInt(bxing)
        writeString(chaodai)
        writeString(langsongAuthorPY)
        writeInt(dxing)
        writeInt((if (yizhuYuanchuang) 1 else 0))
        writeString(yizhuAuthor)
        writeInt(id)
        writeString(tag)
        writeString(cont)
        writeInt((if (shangIspass) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GushiwensItem> = object : Parcelable.Creator<GushiwensItem> {
            override fun createFromParcel(source: Parcel): GushiwensItem = GushiwensItem(source)
            override fun newArray(size: Int): Array<GushiwensItem?> = arrayOfNulls(size)
        }
    }
}