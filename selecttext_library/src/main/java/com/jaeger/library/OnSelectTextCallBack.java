package com.jaeger.library;

import android.content.Context;

/**
 * Created by Jaeger on 16/8/30.
 * <p>
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
public class OnSelectTextCallBack implements OnSelectListener {
    Context mContext;

    public OnSelectTextCallBack(Context context) {
        this.mContext = context;
    }

    @Override
    public void onTextSelected(CharSequence content) {

    }

    @Override
    public void onTextShare(CharSequence content) {

    }
}
