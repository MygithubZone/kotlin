package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable

data class AuthorListMoel(val sumCount: Int = 0,
                          val sumPage: Int = 0,
                          val pageTitle: String? = null,
                          val currentPage: Int = 0,
                          val keyStr: String = "",
                          val authors: ArrayList<AuthorsItem>? = null,
                          val masterTitle: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            ArrayList<AuthorsItem>().apply { source.readList(this, AuthorsItem::class.java.classLoader) },
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(sumCount)
        writeInt(sumPage)
        writeString(pageTitle)
        writeInt(currentPage)
        writeString(keyStr)
        writeList(authors)
        writeString(masterTitle)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<AuthorListMoel> = object : Parcelable.Creator<AuthorListMoel> {
            override fun createFromParcel(source: Parcel): AuthorListMoel = AuthorListMoel(source)
            override fun newArray(size: Int): Array<AuthorListMoel?> = arrayOfNulls(size)
        }
    }
}