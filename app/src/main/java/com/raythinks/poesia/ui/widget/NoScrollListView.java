package com.raythinks.poesia.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 大灯泡 on 2016/2/27.
 * listview嵌套的gridview
 */
public class NoScrollListView extends ListView {
    private static final String TAG = "NoScrollGridView";

    public NoScrollListView(Context context) {
        this(context,null);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;

        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            heightSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
