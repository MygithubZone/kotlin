package com.raythinks.poesia.utils

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/10/25 0025<br>.
 * 版本：1.2.0
 */
object SearchHistoryUtils {
    fun initData(){
        String longHistory = (String) SPUtils.get(SEARCH_HISTORY, "");
        String[] tmpHistory = longHistory.split(",");                            //split后长度为1有一个空串对象
        historyList = new ArrayList<String>(Arrays.asList(tmpHistory));

        if (historyList.size() == 1 && historyList.get(0).equals("")) {          //如果没有搜索记录，split之后第0位是个空串的情况下
            historyList.clear();                                                 //清空集合，这个很关键
        }
    }
    /**
     * 保存搜索记录
     *
     * @param inputText 输入的历史记录
     */
    private void saveSearchHistory(String inputText) {

        if (TextUtils.isEmpty(inputText)) {
            return;
        }

        String longHistory = (String) SPUtils.get(SEARCH_HISTORY, "");        //获取之前保存的历史记录

        String[] tmpHistory = longHistory.split(",");                            //逗号截取 保存在数组中

        historyList = new ArrayList<String>(Arrays.asList(tmpHistory));          //将改数组转换成ArrayList

        if (historyList.size() > 0) {
            //移除之前重复添加的元素
            for (int i = 0; i < historyList.size(); i++) {
                if (inputText.equals(historyList.get(i))) {
                    historyList.remove(i);
                    break;
                }
            }

            historyList.add(0, inputText);                           //将新输入的文字添加集合的第0位也就是最前面

            if (historyList.size() > 10) {
                historyList.remove(historyList.size() - 1);         //最多保存10条搜索记录 删除最早搜索的那一项
            }

            //逗号拼接
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < historyList.size(); i++) {
                sb.append(historyList.get(i) + ",");
            }
            //保存到sp
            SPUtils.put(SEARCH_HISTORY, sb.toString());
        } else {
            //之前未添加过
            SPUtils.put(SEARCH_HISTORY, inputText + ",");
        }
    }
    fun clear(){
        historyList.clear();                            //清空集合
        SPUtils.remove(SEARCH_HISTORY);                 //清空sp
        mHistorySearchAdapter.notifyDataChanged();      //刷新适配器
    }
}