package com.example.doulai.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CMCC-ZHENGCHENG on 2017/3/14.
 */

public interface ViewParser {
    public View inflate(Context context, ViewGroup parent, boolean attachToRoot);
}
