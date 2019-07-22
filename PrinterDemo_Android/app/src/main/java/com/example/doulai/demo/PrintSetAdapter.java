package com.example.doulai.demo;

import android.content.Context;
import android.view.View;




/**
 * Created by CMCC-ZHENGCHENG on 2017/4/6.
 */

public class PrintSetAdapter extends CommonAdapter<PrintProperty, PrintSetViewHolder> {
    Context mcontext;
    public PrintSetAdapter(Context context) {
        super(context);
        mcontext=context;
    }

    @Override
    protected PrintSetViewHolder createViewHolder() {
        return new PrintSetViewHolder();
    }

    @Override
    protected void setItemData(int position, PrintSetViewHolder viewHolder, View rootView) {
        viewHolder.tvItemPrint.setText(mDataSet.get(position).getPrintProperty());
        if(mDataSet.get(position).isPropertyEnabe()){
            viewHolder.ivChoosePrint.setVisibility(View.VISIBLE);
        }else{
            viewHolder.ivChoosePrint.setVisibility(View.GONE);
        }

    }

    public void clickPosition(int positon,int type){
        mDataSet.get(positon).setPropertyEnabe(!mDataSet.get(positon).isPropertyEnabe());
        notifyDataSetChanged();
        //保存数据库
//        if(type==0)
//            HJSharedPreferencesUitls.setPrintProperty(mDataSet.get(positon).isPropertyEnabe(),mDataSet.get(positon).getPrintProperty());
//        else if(type==1)//todo 支持多项目
//            HJSharedPreferencesUitls.saveBoolean(mcontext, "价格",mDataSet.get(positon).isPropertyEnabe());
    }
}
