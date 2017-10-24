package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.kogitune.activity_transition.ActivityTransitionLauncher
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.net.ApiPoesiaList
import com.raythinks.poesia.ui.activitys.PoesiaDetialActivity
import com.raythinks.poesia.ui.adapter.MenuTypeAdapter
import com.raythinks.poesia.ui.adapter.PoesiaAdapter
import com.raythinks.poesia.ui.viewmodel.PoesiaViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.DialogUtils
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.fragment_poesia.*
import kotlinx.android.synthetic.main.item_poesia.view.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class PoesiaFragment : BaseVMFragment<PoesiaViewModel>(), OnItemClickListener, MenuTypeAdapter.OnMenuItemClickListener {
    override fun onItemClick(ad: MenuTypeAdapter, selection: Int, position: Int, itemView: View) {
        var temp = ad!!.itemArray[selection][position]
        if (!TextUtils.equals(type, temp) || !TextUtils.equals(chao, temp) || !TextUtils.equals(xing, temp)) {
            when (ad!!.selectType) {
                0 -> {
                    if ("不限".equals(temp)) {
                        type = ""
                        tv_poesia_type.text = "类型"
                    } else {
                        type = temp
                        tv_poesia_type.text = type
                    }
                }
                1 -> {
                    if ("不限".equals(temp)) {
                        chao = ""
                        tv_poesia_chao.text = "朝代"
                    } else {
                        chao = temp
                        tv_poesia_chao.text = chao
                    }
                }
                2 -> {
                    if ("不限".equals(temp)) {
                        xing = ""
                        tv_poesia_xing.text = "形式"
                    } else {
                        xing = temp
                        tv_poesia_xing.text = xing
                    }
                }
            }
            stl.showLoading()
            adapter.clearData()
            viewModel.updatePoesiaList(1, type, chao, xing)
        }
        mSheetDialog?.dismiss()
    }

    override fun onItemClick(position: Int, itemView: View) {
        var intent = Intent(_mActivity, PoesiaDetialActivity::class.java)
        intent.putExtra("poesiaItem", adapter.data[position])
        ActivityTransitionLauncher.with(_mActivity).from(itemView.tv_poesia_content).launch(intent);
    }

    lateinit var adapter: PoesiaAdapter

    override fun initView() {
        AnimUtils.loadAmin(_mActivity, cl_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = PoesiaAdapter(viewModel, this)
        recyclerview.setAdapter(adapter)
        refreshLayout.setOnRefreshListener {
            isInitRefresh = true
            viewModel.updatePoesiaList(1, type, chao, xing)
        }
        refreshLayout.setOnLoadmoreListener {
            isInitRefresh = false
            viewModel.updatePoesiaList(currentP + 1, type, chao, xing)
        }
        tv_poesia_type.setOnClickListener {
            mSheetDialog = DialogUtils.initMenuDialog(_mActivity, "分类", 0, typeArray, this@PoesiaFragment)
        }
        tv_poesia_chao.setOnClickListener {

            mSheetDialog = DialogUtils.initMenuDialog(_mActivity, "朝代", 1, caoArray, this@PoesiaFragment)

        }
        tv_poesia_xing.setOnClickListener {
            mSheetDialog = DialogUtils.initMenuDialog(_mActivity, "形式", 2, xingArray, this@PoesiaFragment)

        }
        initDataMenu()
    }

    var mSheetDialog: BottomSheetDialog? = null
    var currentP: Int = 1
    var sumP: Int = 1
    var xing = ""
    var type = ""
    var chao = ""
    override fun initData() {
        stl.showLoading()
        viewModel.updatePoesiaList(currentP, chao, type, xing).observe(this, Observer {
            var gushiwens = it?.gushiwens
            currentP = it?.currentPage ?: 1
            sumP = it?.sumPage ?: 1
            if (gushiwens == null || gushiwens.size == 0) {//返回列表为空
                if (it?.currentPage == 1) {//返回列表为空,且第一页
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
            } else {//返回列表不为空
                if (it?.currentPage == 1) {//第一页
                    stl.showContent()
                }
                refreshLayout.setEnableLoadmore(currentP < sumP)
                adapter.updateData(isInitRefresh, gushiwens)
            }
            refreshLayout.finishRefershOrLoadMore(currentP == 1)
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiPoesiaList -> {
                    TUtils.showToast(it?.msg ?: "aaaa")
                    if (currentP == 1) {
                        stl.showError(it.msg, { initData() })
                    }
                    refreshLayout.finishRefershOrLoadMore(currentP == 1)
                    return@Observer
                }
            }
        })
    }

    lateinit var typeArray: Array<String>
    lateinit var xingArray: Array<String>
    lateinit var caoArray: Array<String>
    fun initDataMenu() {
        caoArray = resources.getStringArray(R.array.array_author_borntimes)
        xingArray = arrayOf("不限", "诗词", "曲", "文言文")
        typeArray = arrayOf("不限", "写景", "咏物", "春天", "夏天", "秋天", "冬天", "写雨", "写雪", "写风", "写花",
                "梅花", "荷花", "菊花", "柳树", "月亮", "山水", "长江", "黄河",  "写鸟",
                "田园",  "地名", "抒情", "爱国", "离别", "送别", "思乡", "思念", "爱情", "励志", "哲理","悼亡",
                "写人", "老师", "母亲", "友情", "战争", "读书", "惜时", "婉约", "豪放", "诗经", "春节", "元宵节",
                "寒食节", "清明节", "端午节", "七夕节", "中秋节", "重阳节", "忧国忧民", "咏史怀古", "宋词精选", "小学古诗",
                "初中古诗", "高中古诗", "小学文言文", "初中文言文", "高中文言文", "古诗十九首", "唐诗三百首", "古诗三百首",
                "宋词三百首", "古文观止")

//        typeArray = arrayOf("写景", "婉约", "咏物", "抒情", "写人", "唐诗三百首", "诗经", "抒怀",
//                "宋词三百首", "思念", "古诗三百首", "怀古", "送别", "生活", "春天", "女子", "古文观止"
//                , "宋词精选", "乐府", "离别", "秋天", "思乡", "故事", "爱情", "哲理", "咏史", "孤独",
//                "爱国", "怀人", "感伤", "相思", "友情", "咏史怀古", "组诗", "闺怨", "豪放",
//                "初中文言文", "梅花", "赞美", "写花", "感叹", "边塞", "讽刺", "山水", "忧国",
//                "忧民", "写雨", "战争", "忧国忧民", "初中古诗", "壮志", "人生", "叙事", "赞颂",
//                "妇女", "寓理", "怀念", "写鸟", "月亮", "励志", "柳树", "饮酒", "议论", "重阳节",
//                "伤怀", "写雪", "冬天", "田园", "寓人", "小学古诗", "登高", "感慨", "惜春", "小学文言文",
//                "地名", "伤春", "夏天", "", "写山", "七夕节", "中秋节", "羁旅", "怀才不遇", "寓言", "惜别", "寒食节",
//                "高中文言文", "辞赋精选", "典故", "写水", "荷花", "悼亡", "写风", "春节", "同情", "言志", "宴饮",
//                "思归", "元宵节", "清明节", "历史", "品格", "西湖", "纪游", "壮志难酬", "音乐", "菊花"
//                , "喜悦", "回忆", "评论", "序文", "追忆", "端午节", "民谣", "农民", "友人", "神话", "赠别",
//                "写马", "惜时", "景点", "祭祀", "宴会", "失意", "记梦", "悲秋", "登楼", "高中古诗", "少女", "题画", "怀远",
//                "春游", "读书", "儿童", "岁月", "宫怨", "时光", "月夜", "乡村", "风俗", "民歌", "抱负", "贬谪", "豪迈", "无奈",
//                "长江", "古诗十九首", "书信", "传记", "楚辞", "归隐", "向往", "纪行", "勉励"
//                , "赏花", "劝勉", "命运", "黄河", "长诗", "吊古", "隐居", "学习", "劝谏", "江南", "乐歌", "祝福", "散文",
//                "对话", "农村", "惜花", "狩猎", "寺庙", "节日", "行舟", "怨刺", "吊古伤今", "将士", "理想"
//                , "春愁", "想象", "闲适", "忆旧", "怀旧", "桃花", "竹子", "弃妇", "哀怨", "批判", "访友", "宫廷", "歌女", "忧愁", "述志",
//                "外交", "植物", "怅惘", "愤懑", "人民", "母亲", "教育", "劝慰", "游历", "规劝", "游子", "感恩", "劝诫", "凭吊",
//                "旷达", "悲愤", "渔人", "讽喻", "秋夜", "愤慨", "归乡", "游记", "劳动", "农事", "史论", "青春", "借古讽今", "写云",
//                "隐逸", "牡丹", "离愁", "祝寿", "赏月", "思亲", "美人", "惆怅", "壮志未酬", "声音", "", "感时", "写茶", "垂钓",
//                "踏青", "松树", "仕途", "借景", "抒情", "湖水", "军旅", "自由", "老师", "瀑布", "少年", "颂歌", "婚姻", "写鱼", "动物",
//                "诚信", "祝愿", "将军", "游侠", "思国", "写酒", "战士", "春景", "古体", "亲情", "忧伤", "行路", "悼念", "忧思", "忧愤",
//                "志向", "山村", "用人", "乐曲", "留恋", "慰勉", "水乡", "侠客", "寻访", "考试", "旅途", "愁思", "叙梦", "感悟", "池塘",
//                "行军", "咏怀", "愿望", "伤别", "亲人", "凄凉", "游仙", "散曲", "热爱", "望远", "少妇", "追求", "抒愤", "怀乡", "贫困",
//                "怨愤", "退隐", "爱慕", "祝贺", "怨别", "托志", "月下", "雨夜", "对月", "怀古伤今", "舞蹈", "边疆", "暮春", "文学",
//                "思想", "品质", "写草", "自白", "哀歌", "哀愁", "劳作", "身世", "庐山", "新婚", "赠答", "渡江", "白菊", "理论"
//                , "地点", "伤感", "即景", "感怀", "宽慰", "忠贞", "坚贞", "惋惜", "依恋", "眷恋", "和诗", "喝酒", "父母", "迁谪", "求贤", "感激",
//                "公文", "赠序", "祭文", "歌辞", "神仙", "人格", "寒夜", "谋略", "生死", "喜爱", "男子", "孝道", "道士", "哲学", "奏议",
//                "游说", "仁政", "禅隐", "竞渡", "和平", "治国", "修养", "忧郁", "春闺", "传说", "童谣", "表演", "游园", "月色", "英雄",
//                "墓志铭", "碑文", "劝告", "赞语", "策略", "檄文", "期望", "落花", "渡河", "食物", "写诗", "观点", "联想", "山峰", "哀悼",
//                "山林", "悲愁", "唱和", "自传", "情怀", "时间", "夫妻", "建功立业", "鼓励", "关怀", "官吏"
//                , "征人", "歌颂", "寓情", "梨花", "拟古", "即景抒情", "家乡", "赠诗", "写鬼", "哀叹", "乡思", "桂花", "怨妇", "留别", "隐士", "怀恋",
//                "闺情", "歌谣", "状物", "望月", "景物", "看花", "山川", "题咏", "题诗", "艳情", "酬赠", "邀请", "援引", "酬答", "乐器",
//                "民族", "春光", "哀思", "早朝", "愉悦", "艺术", "贫穷", "离恨", "怀春", "写桥", "祈祷", "悲壮", "写树", "悲叹", "叹息",
//                "抒志", "送春", "愁苦", "感时伤事", "告诫", "苦闷", "抚今忆昔", "渔父", "习俗", "游春", "情思", "故乡", "谪居", "迎春",
//                "盼春", "重逢", "艰难", "生命", "歌咏", "苦难", "伤悼", "纳谏", "旅客", "悲痛", "骚体", "月光", "嘲讽", "念旧", "咏志",
//                "美女", "思慕", "江河", "委婉", "朋友", "谢绝", "寂寞", "秋日", "宦游", "黄昏", "花落", "春残", "闺思", "恩师",
//                "伤今感昔", "夕阳", "橘子", "游赏", "怀今", "", "妻子", "惜人", "寻春", "心情", "杜鹃", "闲居", "悲苦", "写梅", "懊悔",
//                "抚今思昔", "借古寄怀", "伤今追昔", "早春", "春意", "行旅", "抒己", "叹恨", "借古论今", "演习", "团聚", "鼓舞", "慨叹",
//                "不满", "故国", "思索", "答别", "抚事", "企盼", "感概", "邀客", "写灯", "", "恋情", "祈雨", "他乡", "故人", "抚今追昔",
//                "悔恨", "题赠", "舟中", "时事", "长篇", "艰苦", "关塞", "杂诗", "大自然", "别离", "组曲", "腊梅", "旅人", "遣怀", "赏春",
//                "恨别", "离情", "登山", "书法", "新乐府", "牧童", "忠义", "怀古伤心", "怀友", "矛盾", "待客", "士卒", "花草", "杯古",
//                "话旧", "相聚", "军人", "触景", "生情", "山乡", "哀伤", "年老", "失恋", "借凭吊屈", "孤单", "风土人情", "折柳",
//                "茶叶", "母爱", "情郎", "咏竹", "咏剑", "梦游", "月宫", "赋税", "悼惜", "祈盼", "祝辞", "悲歌", "君臣", "民情",
//                "螃蟹", "郊游", "叙旧", "豪壮", "寓志", "柳絮", "感昔伤今", "调笑", "风雨", "怨情", "托古讽今", "赠友", "悲剧",
//                "凭吊", "古迹", "豪情", "聚会", "寄语", "知识", "怜悯", "垂柳", "荷叶", "日出", "秋千", "寓情于景", "情歌", "托物寄情",
//                "秋雨", "戍边", "豪侠", "隐者", "钓鱼", "湖泊", "君主", "应制", "酬和", "侠义", "复仇", "触景伤情", "暮年", "客人",
//                "触景感怀", "以景衬情", "杂言", "安慰", "奔放", "遗憾", "知己", "青年", "落寞", "敬爱", "儿女", "记游", "泰山", "沙漠",
//                "社交", "忧患", "肖像", "咏叹", "赞扬", "大雁", "写塔", "志士", "秋游", "精神", "拜访", "流放", "洞庭湖", "游猎",
//                "写柳", "悼古伤今", "乐舞", "祷词", "婚恋", "启示", "铭文", "奴隶", "写羊", "父亲", "写雷", "修行", "美学", "交友",
//                "长城", "友好", "咏古", "春夜", "南方", "歌妓", "图画", "寻花", "丰收", "雨后", "相逢", "借古伤今", "愤恨", "劝学",
//                "解释", "争论", "祝酒", "荐书", "推崇", "儒道", "避讳", "说理", "农业", "用兵", "策论", "祭奠", "朋党", "史评", "星星",
//                "感物兴怀", "送葬", "鬼魂", "战乱", "迁徙", "悲伤", "知音", "地震", "爱民", "杨柳", "燕子", "客愁", "以古讽今",
//                "四季", "采莲", "赠言", "儿歌", "性格", "初夏", "山居", "荔枝", "札记", "留客", "关切", "秋景", "思怀", "礼教",
//                "帝王", "荷塘", "惭愧", "梦想", "托物", "抒怀", "恋人", "画竹", "江峡", "写狼", "莲花", "技艺", "自述", "童年",
//                "选官", "制度", "态度", "夜景", "闻声", "国事", "", "伤老", "认知", "道德", "小品", "渴望", "", "凄苦", "珍惜",
//                "出征", "工人", "海棠", "杂剧", "汤圆", "游玩", "往事", "暮归", "怨恨", "秋思", "船夫", "盼望", "贞洁", "太阳",
//                "抚昔", "伤今", "忠告", "悲凉", "名利", "归家", "愁恨", "谴责", "边将", "乡风", "种树", "小说", "感想", "文集", "碑记", "梦境", "深秋")

    }

    override fun getLayoutId(): Int = R.layout.fragment_poesia
}