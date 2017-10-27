package com.raythinks.poesia.ui.adapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.ui.viewmodel.BasePoesiaViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.item_poesia.view.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class PoesiaAdapter(var owner: LifecycleOwner, var viewModel: BasePoesiaViewModel, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<PoesiaAdapter.ViewHolder>() {
    var data: ArrayList<GushiwensItem>

    init {
        data = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.tv_poesia_title.text = data[position].nameStr
        holder!!.itemView.tv_poesia_author.text = "${data[position].chaodai}.${data[position].author}"
        var selectHelper = TUtils.copyText(holder!!.itemView.context, holder!!.itemView.tv_poesia_content)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position, holder.itemView)
        }
        holder.itemView.tv_poesia_content.setOnClickListener {
            if (selectHelper.isShowPopMenu) {
                selectHelper.closeMenu()
            } else {
                onItemClickListener.onItemClick(position, holder.itemView)
            }
        }
        if (data[position].yizhuIspass) {
            holder!!.itemView.ll_yizhu.visibility = View.VISIBLE
        } else {
            holder!!.itemView.ll_yizhu.visibility = View.GONE
        }
        var isYi = false
        var isZhu = false
        var cont: String? = ""
        when (data[position].showType) {
            "cont" -> {
                isYi = false
                isZhu = false
                cont = data[position].cont
            }
            "yi" -> {
                isYi = true
                isZhu = false
                cont = data[position].yizhuCont?.cont
            }
            "zhu" -> {
                isYi = false
                isZhu = true
                cont = data[position].yizhuCont?.cont
            }
            "yizhu" -> {
                isYi = true
                isZhu = true
                cont = data[position].yizhuCont?.cont
            }
        }
        holder!!.itemView.tv_poesia_content.text = Html.fromHtml(TUtils.convertPoesia(cont ?: ""))
        holder!!.itemView.tv_yi.isSelected = isYi
        holder!!.itemView.tv_zhu.isSelected = isZhu
        holder!!.itemView.tv_yi.setOnClickListener {
            var type = "cont"
            when (data[position].showType) {
                "cont" -> {
                    type = "yi"
                }
                "yi" -> {
                    type = "cont"
                }
                "zhu" -> {
                    type = "yizhu"
                }
                "yizhu" -> {
                    type = "zhu"
                }
            }
            viewModel.updatePoesiaContent(data[position].id.toString(), type).observe(owner, Observer {
                data[position].yizhuCont = it!!
                data[position].showType = type
                notifyItemChanged(position)
            })

        }
        holder!!.itemView.tv_zhu.setOnClickListener {
            var type = "cont"
            when (data[position].showType) {
                "cont" -> {
                    type = "zhu"
                }
                "yi" -> {
                    type = "yizhu"
                }
                "zhu" -> {
                    type = "cont"
                }
                "yizhu" -> {
                    type = "yi"
                }
            }
            viewModel.updatePoesiaContent(data[position].id.toString(), type).observe(owner, Observer {
                data[position].yizhuCont = it!!
                data[position].showType = type
                notifyItemChanged(position)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PoesiaAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_poesia, parent, false))
    }

    override fun getItemCount(): Int = data.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun updateData(b: Boolean, newData: ArrayList<GushiwensItem>) {
        if (data.size == 0) {
            data.addAll(newData)
            notifyItemRangeInserted(0, data.size)
        } else {
            if (b) {
                data.clear()
                data.addAll(newData)
                notifyDataSetChanged()
            } else {
                var oldSize = data.size
                data.addAll(oldSize, newData)
                notifyItemRangeInserted(oldSize, data.size - oldSize)
            }
        }
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }
}