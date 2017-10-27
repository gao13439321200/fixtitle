package com.example.linlin.test0520;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by linlin on 2017/10/28.
 */

public class MyListView extends ListView {

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
