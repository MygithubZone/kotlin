package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raythinks.poesia.R
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter
import kotlinx.android.synthetic.main.item_libors_mulu.view.*
import java.util.ArrayList

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class RefranesTypeAdapter(context: Context, onItemClickListener: OnSelectionItemClickListener) : SimpleSectionedAdapter<RefranesTypeAdapter.ViewHolder>() {
    var typeArray = ArrayList<Array<String>>()
    var themeArray: Array<String>
    var itemArray = ArrayList<Array<String>>()
    var mItemClickListener: OnSelectionItemClickListener
    var theme = "";
    var type = "";
    var header: Array<String>? = null
    var isTheme: Boolean = true

    init {
        mItemClickListener = onItemClickListener
        themeArray = arrayOf("不限", "抒情", "四季", "山水", "天气", "人物", "人生", "生活", "节日", "动物", "植物", "食物")
        typeArray.add(arrayOf("不限", "爱情", "友情", "离别", "思念", "思乡", "伤感", "孤独", "闺怨", "悼亡", "怀古", "爱国", "感恩"))
        typeArray.add(arrayOf("不限", "春天", "夏天", "秋天", "冬天"))
        typeArray.add(arrayOf("不限", "庐山", "泰山", "江河", "长江", "黄河", "西湖", "瀑布"))
        typeArray.add(arrayOf("不限", "写风", "写云", "写雨", "写雪", "彩虹", "太阳", "月亮", "星星"))
        typeArray.add(arrayOf("不限", "女子", "父亲", "母亲", "老师", "儿童"))
        typeArray.add(arrayOf("不限", "励志", "哲理", "青春", "时光", "梦想", "读书", "战争"))
        typeArray.add(arrayOf("不限", "乡村", "田园", "边塞", "写桥"))
        typeArray.add(arrayOf("不限", "春节", "元宵节", "寒食节", "清明节", "端午节", "七夕节", "中秋节", "重阳节"))
        typeArray.add(arrayOf("不限", "写鸟", "写马", "写猫"))
        typeArray.add(arrayOf("不限", "梅花", "梨花", "桃花", "荷花", "菊花", "柳树", "叶子", "竹子"))
        typeArray.add(arrayOf("不限", "写酒", "写茶", "荔枝"))
        itemArray.add(themeArray)
    }

    fun updateData(theme: String, type: String, isTheme: Boolean) {
        if (isTheme) {
            itemArray.set(0, themeArray)
        } else {
            itemArray.set(0, typeArray[themeArray.indexOf(theme) - 1])
        }
        this.theme = theme
        this.type = type
        this.isTheme = isTheme
        notifyDataSetChanged()
    }

    override fun getSectionHeaderTitle(section: Int): String {
        return theme
    }

    override fun onBindItemViewHolder(holder: ViewHolder?, section: Int, position: Int) {
        holder!!.itemView.tv_libros_mulu.text = itemArray[section]!![position]
        holder!!.itemView.tv_libros_mulu.setOnClickListener { mItemClickListener.onItemClick(section, position, it) }
    }

    override fun onCreateItemViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_libors_mulu, parent, false))
    }

    override fun getLayoutResource() = R.layout.header_textview
    override fun getTitleTextID() = R.id.tv_header_item
    override fun getSectionCount() = 1
    override fun getItemCountForSection(section: Int): Int {
        return itemArray[section]!!.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}