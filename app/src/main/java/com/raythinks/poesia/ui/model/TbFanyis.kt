package com.raythinks.poesia.ui.model
import android.os.Parcel
import android.os.Parcelable

data class TbFanyis(val fanyis: ArrayList<FanyisItem>? = null,
                    val masterTitle: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            source.createTypedArrayList(FanyisItem.CREATOR),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(fanyis)
        writeString(masterTitle)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TbFanyis> = object : Parcelable.Creator<TbFanyis> {
            override fun createFromParcel(source: Parcel): TbFanyis = TbFanyis(source)
            override fun newArray(size: Int): Array<TbFanyis?> = arrayOfNulls(size)
        }
    }
}