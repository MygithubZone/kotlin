package com.raythinks.poesia.ui.model
import android.os.Parcel
import android.os.Parcelable

data class TbShangxis(val shangxis: ArrayList<ShangxisItem>? = null,
                      val masterTitle: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            ArrayList<ShangxisItem>().apply { source.readList(this, ShangxisItem::class.java.classLoader) },
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(shangxis)
        writeString(masterTitle)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TbShangxis> = object : Parcelable.Creator<TbShangxis> {
            override fun createFromParcel(source: Parcel): TbShangxis = TbShangxis(source)
            override fun newArray(size: Int): Array<TbShangxis?> = arrayOfNulls(size)
        }
    }
}