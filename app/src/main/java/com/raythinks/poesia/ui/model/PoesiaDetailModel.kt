package com.raythinks.poesia.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.raythinks.poesia.ui.model.AuthorsItem
import com.raythinks.poesia.ui.model.GushiwensItem

data class PoesiaDetailModel(
        val tb_gushiwen: GushiwensItem? = null,
        val tb_shangxis: TbShangxis? = null,
        val tb_author: AuthorsItem? = null,
        val tb_fanyis: TbFanyis? = null, var tb_mingju: MingjusItem? = null) : Parcelable {
    constructor(source: Parcel) : this(
            source.readParcelable<GushiwensItem>(GushiwensItem::class.java.classLoader),
            source.readParcelable<TbShangxis>(TbShangxis::class.java.classLoader),
            source.readParcelable<AuthorsItem>(AuthorsItem::class.java.classLoader),
            source.readParcelable<TbFanyis>(TbFanyis::class.java.classLoader),
            source.readParcelable<MingjusItem>(MingjusItem::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(tb_gushiwen, 0)
        writeParcelable(tb_shangxis, 0)
        writeParcelable(tb_author, 0)
        writeParcelable(tb_fanyis, 0)
        writeParcelable(tb_mingju, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PoesiaDetailModel> = object : Parcelable.Creator<PoesiaDetailModel> {
            override fun createFromParcel(source: Parcel): PoesiaDetailModel = PoesiaDetailModel(source)
            override fun newArray(size: Int): Array<PoesiaDetailModel?> = arrayOfNulls(size)
        }
    }
}