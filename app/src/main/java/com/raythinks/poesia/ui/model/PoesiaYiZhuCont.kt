package com.raythinks.poesia.ui.model
import android.os.Parcel
import android.os.Parcelable

data class PoesiaYiZhuCont(val cankao: String = "",
                           val id: Int = 0,
                           val cont: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(cankao)
        writeInt(id)
        writeString(cont)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PoesiaYiZhuCont> = object : Parcelable.Creator<PoesiaYiZhuCont> {
            override fun createFromParcel(source: Parcel): PoesiaYiZhuCont = PoesiaYiZhuCont(source)
            override fun newArray(size: Int): Array<PoesiaYiZhuCont?> = arrayOfNulls(size)
        }
    }
}