package com.example.doulai.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by
 */

public abstract class BaseViewHolder implements ViewParser {


    public View itemView;
    protected Context mContext;
    protected ViewFinder mViewFinder;

    @Override
    public final View inflate(Context context, ViewGroup parent, boolean attachToRoot) {
        mContext = context;
        itemView = LayoutInflater.from(context).inflate(getItemLayout(), parent, attachToRoot);
        // 设置tag
        itemView.setTag(this);
        mViewFinder = new ViewFinder(itemView);
        initWidgets();
        return itemView;
    }

    /**
     * 获取ItemView的布局Id
     *
     * @return Item View布局
     */
    protected abstract int getItemLayout();

    /**
     * 初始化各个子视图
     */
    protected abstract void initWidgets();

    /**
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(int id) {
        return mViewFinder.findViewById(id);
    }

}
