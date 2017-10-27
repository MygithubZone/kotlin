package com.raythinks.poesia.ui.model
import android.os.Parcel
import android.os.Parcelable

data class ShangxisItem(val cankao: String = "",
                        val nameStr: String = "",
                        val author: String = "",
                        val shiID: Int = 0,
                        val isYuanchuang: Boolean = false,
                        val id: Int = 0,
                        val ok: Int = 0,
                        val cont: String = "",
                        val noOk: Int = 0, var showType: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            1 == source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(cankao)
        writeString(nameStr)
        writeString(author)
        writeInt(shiID)
        writeInt((if (isYuanchuang) 1 else 0))
        writeInt(id)
        writeInt(ok)
        writeString(cont)
        writeInt(noOk)
        writeInt(showType)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ShangxisItem> = object : Parcelable.Creator<ShangxisItem> {
            override fun createFromParcel(source: Parcel): ShangxisItem = ShangxisItem(source)
            override fun newArray(size: Int): Array<ShangxisItem?> = arrayOfNulls(size)
        }
    }
}