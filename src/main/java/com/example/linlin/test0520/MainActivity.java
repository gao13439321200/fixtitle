package com.example.linlin.test0520;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import static com.example.linlin.test0520.R.id.rl_middle;

public class MainActivity extends Activity {
    private Button mTvMiddle;
    private RelativeLayout mTopMiddle;
    private RelativeLayout mRlMiddle;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        mRlMiddle = (RelativeLayout) findViewById(rl_middle);
        mTvMiddle = (Button) findViewById(R.id.tv_middle);
        MyListView listView = (MyListView) findViewById(R.id.listview);
        view = findViewById(R.id.view_middle);
        mTopMiddle = (RelativeLayout) findViewById(R.id.rl_top);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("这是第" + (i + 1) + "个傻瓜");
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list));

        //scrollview的滑动监听事件
        //只有sdk版本高于23时才可以使用setOnScrollChangeListener
        //低于23时需使用scrollView.getViewTreeObserver().addOnScrollChangedListener
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //两种定位获取控件到边缘的方法：
                //1、推荐使用Rect，Rect可以获取到控件到系统栏下边缘的高度，体验度比第二种方法好
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);//获取固定栏顶部到屏幕顶部的高度，推荐在需要固定的布局上面添加一个view用于屏幕定位
                //2、使用getLocationOnScreen获取控件在屏幕上的坐标 locations[0]为横坐标，locations[1]为纵坐标
//                int[] locations = new int[2];
//                view.getLocationOnScreen(locations);
                ViewGroup parent = (ViewGroup) mTvMiddle.getParent();//获取固定栏的父布局
                if (rect.top < 0) {//高度小于0，说明控件出屏幕了，需要固定了
                    if (parent != null && parent.getId() == R.id.rl_middle) {//只有固定栏的父布局是原布局的时候才加载到置顶布局中
                        parent.removeAllViews();
                        mTopMiddle.addView(mTvMiddle);
                    }
                } else {//高度大于0，回到屏幕了，需要取消固定了
                    if (parent != null && parent.getId() == R.id.rl_top) {//只有固定栏的父布局是置顶布局的时候才加载到原布局中
                        parent.removeAllViews();
                        mRlMiddle.addView(mTvMiddle);
                    }
                }
            }

        });

    }
}
