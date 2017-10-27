package com.raythinks.poesia.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.model.FanyisItem
import com.raythinks.poesia.ui.model.ShangxisItem
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import kotlinx.android.synthetic.main.item_poesia_fanshang.view.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class PoesiaTranslateAdapter(var viewHodler: PoesiaDetialViewModel) : RecyclerView.Adapter<PoesiaTranslateAdapter.ViewHolder>() {
    var data: ArrayList<FanyisItem>

    init {
        data = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_content.text = Html.fromHtml(data[position].cont)
        holder!!.itemView.tv_title.text = data[position].nameStr
        holder!!.itemView.tv_more.visibility = View.VISIBLE
        if (data[position].showType == 0) {
            holder!!.itemView.tv_more.text = "阅读全部"
            holder!!.itemView.tv_cankao.visibility = View.GONE
            holder!!.itemView.tv_content.setEllipsize(TextUtils.TruncateAt.END); // 展开
        } else {
            holder!!.itemView.tv_content.setEllipsize(null); // 展开
            holder!!.itemView.tv_content.setSingleLine(false);
            holder!!.itemView.tv_more.text = "收起全部"
            var cankao = data[position].cankao
            if (!TextUtils.isEmpty(cankao)) {
                var cankaoArray = cankao.split("&")
                var index = 1;
                var cankaoFinalStr = "<B>参考</B><br/>"
                for (str in cankaoArray) {
                    cankaoFinalStr = cankaoFinalStr + "${index}.${str}<br/>"
                    index++
                }
                holder!!.itemView.tv_cankao.visibility = View.VISIBLE
                holder!!.itemView.tv_cankao.text = Html.fromHtml(cankaoFinalStr)
            } else {
                holder!!.itemView.tv_cankao.visibility = View.GONE
            }
        }
        holder!!.itemView.tv_more.setOnClickListener {
            if (data[position].showType == 0) {
                data[position].showType = 1
            } else {
                data[position].showType = 0
            }
            notifyItemChanged(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PoesiaTranslateAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_poesia_fanshang, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun updateData(newData: ArrayList<FanyisItem>) {
        data.addAll(0, newData)
        notifyItemRangeInserted(0, data.size)
    }
}