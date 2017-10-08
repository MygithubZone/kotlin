package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable

data class AuthorsItem(val ipStr: String? = null,
                       val chaodai: String = "",
                       val nameStr: String = "",
                       val id: Int = 0,
                       val pic: String = "",
                       val creTime: String = "",
                       val cont: String = "",
                       val likes: Int = 0) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(ipStr)
        writeString(chaodai)
        writeString(nameStr)
        writeInt(id)
        writeString(pic)
        writeString(creTime)
        writeString(cont)
        writeInt(likes)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<AuthorsItem> = object : Parcelable.Creator<AuthorsItem> {
            override fun createFromParcel(source: Parcel): AuthorsItem = AuthorsItem(source)
            override fun newArray(size: Int): Array<AuthorsItem?> = arrayOfNulls(size)
        }
    }
}