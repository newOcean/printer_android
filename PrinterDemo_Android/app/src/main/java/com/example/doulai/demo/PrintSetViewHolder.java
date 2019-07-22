package com.example.doulai.demo;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.doulai.demo.BaseViewHolder;
import com.example.doulai.printersdk.R;


/**
 * Created by CMCC-ZHENGCHENG on 2017/4/6.
 */

public class PrintSetViewHolder extends BaseViewHolder {

    public TextView tvItemPrint;
    public ImageView ivChoosePrint;
    @Override
    protected int getItemLayout() {
        return R.layout.item_print_set;
    }

    @Override
    protected void initWidgets() {
        tvItemPrint = findViewById(R.id.tv_print_set_name);
        ivChoosePrint = findViewById(R.id.iv_print_set_choose);
    }
}
