package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.View
import com.bumptech.glide.Glide
import com.huxq17.swipecardsview.BaseCardAdapter
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import kotlinx.android.synthetic.main.item_authorlist.view.*
import com.dd.morphingbutton.MorphingButton
import com.raythinks.poesia.ui.model.AuthorListMoel
import com.raythinks.poesia.ui.model.AuthorsItem
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.utils.ImageUtils
import com.raythinks.poesia.utils.TUtils


/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class AuthorListAdapter(context: Context, viewModel: AuthorListViewModel, data: ArrayList<AuthorsItem>? = ArrayList<AuthorsItem>()) : BaseCardAdapter<String>() {
    var pics: Array<Int>;
    var circle: MorphingButton.Params
    var square: MorphingButton.Params
    var data: ArrayList<AuthorsItem>?
    var mContext: Context

    init {
        mContext = context
        this.data = data;
        pics = arrayOf(R.mipmap.libai, R.mipmap.baijuyi, R.mipmap.dufu)
        circle = MorphingButton.Params.create()
                .duration(500)
                .cornerRadius(TUtils.dip2px(context, 56f)) // 56 dp
                .width(TUtils.dip2px(context, 56f)) // 56 dp
                .height(TUtils.dip2px(context, 56f)) // 56 dp
                .color(ContextCompat.getColor(context, R.color.colorPrimary)) // normal state color
                .colorPressed(ContextCompat.getColor(context, R.color.colorPrimary)) // pressed state color
                .icon(R.drawable.ic_done_white_24dp) // icon
        square = MorphingButton.Params.create()
                .duration(500)
                .cornerRadius(TUtils.dip2px(context, 2f))
                .width(TUtils.dip2px(context, 60f))
                .height(TUtils.dip2px(context, 28f))
                .color(ContextCompat.getColor(context, R.color.colorPrimary))
                .colorPressed(ContextCompat.getColor(context, R.color.colorPrimary))
                .text(context.resources.getString(R.string.str_collection));
    }

    override fun getCount() = data!!.size ?: 0


    override fun onBindData(position: Int, cardview: View) {
        cardview.mhb_collection.setOnClickListener {
            circle.duration(500)
            cardview.mhb_collection.morph(circle)
        }
        square.duration(0)
        square.icon(0)
        cardview.mhb_collection.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        cardview.mhb_collection.setPadding(0, 0, 0, 0);
        cardview.mhb_collection.morph(square)
        ImageUtils.loadPoesiaPic(mContext, data!![position].pic, cardview.civ_author_header)
        cardview.tv_author_name.text = data!![position].nameStr
        cardview.tv_author_brief.text = Html.fromHtml(data!![position].cont)
    }

    override fun getCardLayoutId(): Int = R.layout.item_authorlist
}