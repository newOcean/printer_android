package com.example.doulai.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.doulai.hjbtprintersdk.HJBluetoothPrintSDK;
import com.example.doulai.printersdk.R;

import java.util.ArrayList;

/**
 * Created by
 */

public class SetPrintTemplateActivity extends BaseActivity {

    public View viewHeader;
    public ListView lvPrintSet;
    public PrintSetAdapter printSetAdapter;

    public RelativeLayout rlPrintSave;
    public TextView tvPrintSave;
    public RelativeLayout rlPrintSetCustom;
    public TextView tvPrintSetCustom;
    public RelativeLayout rlPageStart;
    public TextView tvPageStart;

    public RelativeLayout rlPageEnd;
    public TextView tvPageEnd;
    public RelativeLayout rlPrintTwo;
    public TextView tvPrintTwo;


    public RelativeLayout rlPrinterLenth;
    public TextView tvPrinterLenth;
    public RelativeLayout rlPrinterFont;
    public TextView tvPrinterFont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_print);
        viewHeader = LayoutInflater.from(mContext).inflate(R.layout.item_set_print_header,null,false);
        lvPrintSet = (ListView)findViewById(R.id.lv_print_set);
        rlPrintSave = (RelativeLayout) viewHeader.findViewById(R.id.rl_print_set_save);
        tvPrintSave = (TextView)viewHeader.findViewById(R.id.tv_print_set_save);
        rlPrintSetCustom = (RelativeLayout)viewHeader.findViewById(R.id.rl_print_set_custom);
        tvPrintSetCustom = (TextView)viewHeader.findViewById(R.id.tv_print_set_custom);
//add
        rlPageStart = (RelativeLayout)viewHeader.findViewById(R.id.rl_print_set_page_start);
        tvPageStart = (TextView)viewHeader.findViewById(R.id.tv_print_set_page_start);

        rlPageEnd = (RelativeLayout)viewHeader.findViewById(R.id.rl_print_set_page_end);
        tvPageEnd = (TextView)viewHeader.findViewById(R.id.tv_print_set_page_end);
        rlPrintTwo = (RelativeLayout)viewHeader.findViewById(R.id.rl_print_set_two);
        tvPrintTwo = (TextView)viewHeader.findViewById(R.id.tv_print_set_two);
        rlPrinterLenth = (RelativeLayout)viewHeader.findViewById(R.id.rl_printer_lenth);
        tvPrinterLenth = (TextView)viewHeader.findViewById(R.id.tv_printer_lenth);
        rlPrinterFont = (RelativeLayout)viewHeader.findViewById(R.id.rl_printer_font);
        tvPrinterFont = (TextView)viewHeader.findViewById(R.id.tv_printer_font);

        rlPrintSave.setOnClickListener(this);
        rlPrintSetCustom.setOnClickListener(this);
        rlPageEnd.setOnClickListener(this);
        rlPageStart.setOnClickListener(this);
        rlPrintTwo.setOnClickListener(this);
        rlPrinterLenth.setOnClickListener(this);
        rlPrinterFont.setOnClickListener(this);

        tvActBarLeft.setOnClickListener(this);
        tvActBarRight.setOnClickListener(this);


        ///复制
        setIsNoText(tvPrintSave, HJSharedPreferencesUitls.getIsSavePrint(mContext));
        setIsNoText(tvPrintSetCustom,HJSharedPreferencesUitls.getIsPrintCustom(mContext));
        tvPageStart.setText(HJSharedPreferencesUitls.getString(mContext, Constants.SP_PRINT_PAGE_START,Constants.DEFAUL_PAGE_START));
        tvPageEnd.setText(HJSharedPreferencesUitls.getString(mContext, Constants.SP_PRINT_PAGE_END,Constants.DEFAUL_PAGE_END));
        tvPrintTwo.setText(HJSharedPreferencesUitls.getString(mContext,Constants.SP_PRINT_TWO,""));
        lvPrintSet.addHeaderView(viewHeader);
        lvPrintSet.setAdapter(printSetAdapter);
        printSetAdapter.addData(getPropeties());

        lvPrintSet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    return;
                }
                printSetAdapter.clickPosition(i-1,0);
            }
        });
        tvPrinterLenth.setText(Constants.PRINT_LENTH_SHOW[HJSharedPreferencesUitls.getInt(mContext,Constants.SP_PRINT_MECHINE,0)]);
        tvPrinterFont.setText(Constants.PRINT_FONT_SHOW[HJSharedPreferencesUitls.getInt(mContext,Constants.SP_PRINT_FONT,0)]);
    }


    private void setIsNoText(TextView tv , boolean enable){
        if(enable){
            tv.setText("是");
        }else{
            tv.setText("否");
        }
    }


    @Override
    protected void initData() {
        super.initData();
        printSetAdapter = new PrintSetAdapter(mContext);

    }


    private ArrayList<PrintProperty>  getPropeties(){
        ArrayList<PrintProperty> list =new ArrayList<>();

        for(int i = 0; i < Constants.DEFAULT_PROPERTY.length; i ++){
            PrintProperty printProperty = new PrintProperty();
            printProperty.setPrintProperty(Constants.DEFAULT_PROPERTY[i]);
            printProperty.setPropertyEnabe(Constants.DEFAULT_PRO_ENABLE[i]);
            list.add(printProperty);
//            MyApplication.instance.getDaoSession().getPrintPropertyDao().save(printProperty);
        }
        return list;
//        return (ArrayList<PrintProperty>) MyApplication.instance.getDaoSession().getPrintPropertyDao().loadAll();
    }

    @Override
    protected String getPageTite() {
        return "设置打印机模板";
    }

    @Override
    protected String getPageLeft() {
        return "<返回";
    }

    @Override
    protected String getPageRight() {
        return "";
    }

    @Override
    public void afterRequestPermisson(int requestCode) {

    }

    @Override
    public void afterDeniedPermisson(int requestCode) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_print_set_save:
                new AlertDialog.Builder(mContext).setTitle("选择").setItems(common, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tvPrintSave.setText(common[i]);
                        HJSharedPreferencesUitls.setIsSavePrint(mContext,common[i].equals("是"));

                    }
                }).show();
                break;
            case R.id.rl_print_set_custom:
                new AlertDialog.Builder(mContext).setTitle("选择").setItems(common, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tvPrintSetCustom.setText(common[i]);
                        HJSharedPreferencesUitls.setIsPrintCustom(mContext,common[i].equals("是"));

                    }
                }).show();
                break;
            case R.id.rl_print_set_page_end: {
                final EditText inputServer = new EditText(mContext);
                inputServer.setFocusable(true);
                inputServer.setText(tvPageEnd.getText().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("输入页脚内容").setView(inputServer).setNegativeButton(
                        "取消", null);
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                String inputName = inputServer.getText().toString();
                                tvPageEnd.setText(inputName);
                                HJSharedPreferencesUitls.saveString(mContext, Constants.SP_PRINT_PAGE_END, inputName);

                            }
                        });
                builder.show();
                break;
            }
            case R.id.rl_print_set_page_start: {
                final EditText inputServer = new EditText(mContext);
                inputServer.setFocusable(true);
                inputServer.setText(tvPageStart.getText().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("输入页眉内容").setView(inputServer).setNegativeButton(
                        "取消", null);
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                String inputName = inputServer.getText().toString();
                                tvPageStart.setText(inputName);
                                HJSharedPreferencesUitls.saveString(mContext, Constants.SP_PRINT_PAGE_START, inputName);

                            }
                        });
                builder.show();
                break;
            }
            case R.id.rl_print_set_two:
                startActivity(new Intent(mContext, QRCodeActivity.class));
                break;
            case R.id.tv_actbar_left:
                finish();
                break;
            case R.id.rl_printer_lenth:
                new AlertDialog.Builder(mContext).setTitle("请选择").setItems(Constants.PRINT_LENTH_SHOW, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //点击后
                        tvPrinterLenth.setText(Constants.PRINT_LENTH_SHOW[i]);
                        HJSharedPreferencesUitls.saveInt(mContext,Constants.SP_PRINT_MECHINE,i);
                    }
                }).show();
                break;
            case R.id.rl_printer_font:
                new AlertDialog.Builder(mContext).setTitle("请选择").setItems(Constants.PRINT_FONT_SHOW, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //点击后
                        tvPrinterFont.setText(Constants.PRINT_FONT_SHOW[i]);
                        HJSharedPreferencesUitls.saveInt(mContext,Constants.SP_PRINT_FONT,i);
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvPrintTwo.setText(HJSharedPreferencesUitls.getString(mContext,Constants.SP_PRINT_TWO,""));
    }

    static final String[] common = {"否","是"};
}
