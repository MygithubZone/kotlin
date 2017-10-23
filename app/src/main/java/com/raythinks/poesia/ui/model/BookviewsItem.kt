package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable

data class BookviewsItem(val fenlei: String = "",
                         val nameStr: String = "",
                         val author: String? = null,
                         val num: Int = 0,
                         val yiyi: Boolean = false,
                         val id: Int = 0,
                         val creTime: String = "",
                         val cont: String? = null,
                         val bookName: String? = null,
                         val bookID: Int = 0) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            1 == source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(fenlei)
        writeString(nameStr)
        writeString(author)
        writeInt(num)
        writeInt((if (yiyi) 1 else 0))
        writeInt(id)
        writeString(creTime)
        writeString(cont)
        writeString(bookName)
        writeInt(bookID)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<BookviewsItem> = object : Parcelable.Creator<BookviewsItem> {
            override fun createFromParcel(source: Parcel): BookviewsItem = BookviewsItem(source)
            override fun newArray(size: Int): Array<BookviewsItem?> = arrayOfNulls(size)
        }
    }
}