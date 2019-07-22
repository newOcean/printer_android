package com.example.doulai.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.doulai.hjbtprintersdk.HJBluetoothPrintSDK;
import com.example.doulai.printersdk.R;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    long lastTime=0;
//    HJBlueToothService mBlueToothService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView print =findViewById(R.id.print_tv);
        TextView connect =findViewById(R.id.connect_tv);
        TextView buy =findViewById(R.id.buy_tv);

        mContext =this;
//        mBlueToothService = new HJBlueToothService();
        print.setOnClickListener(this);
        connect.setOnClickListener(this);
        buy.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.print_tv:
            {
                bluetoothPrint();
            }
//                startActivity(new Intent(mContext, SetPrintTemplateActivity.class));
                break;
            case R.id.connect_tv:
                startActivity(new Intent(mContext, BlueToothActivity.class));
                break;
            case R.id.buy_tv:

                try{
                    HJBluetoothPrintSDK.jumpToBrowser(mContext, "taobao://item.taobao.com/item.htm?id=44270096088&spm=2014.21600712.0.0");
                }catch (Exception e) {
                    HJBluetoothPrintSDK.jumpToBrowser(mContext, "https://shop113684150.taobao.com/?spm=2013.1.1000126.d21.2613f640sdKuT0");
                }
                break;
            default:
                break;
        }
    }

    private void bluetoothPrint(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                doPrint();
            }
        };
        thread.start();
    }

    private void doPrint(){
//        HJBlueToothService mBlueToothService=  HJBluetoothPrintSDK.getInstance().btService;
        if( HJBluetoothPrintSDK.getInstance().isConnected()==false) {
            Intent intentDaYin = new Intent(mContext, BlueToothActivity.class);

//            intentDaYin.putExtra("title", title);
//            intentDaYin.putExtra("content",content);
//            intentDaYin.putExtra("summarize",summarize);
//            intentDaYin.putExtra("pageEnd",pageEnd);
//            if (logo!=null)
//            {
//                ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                logo.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte [] bitmapByte =baos.toByteArray();
//                intentDaYin.putExtra("logo", bitmapByte);
//            }
            startActivity(intentDaYin);
        }else{
            String title ="公司\n";
            String content ="内容\n内容\n";

            String qrCode="www.baidu.com";
//            qrCode =null;

            HJBluetoothPrintSDK.getInstance().cleanBuffer();
            HJBluetoothPrintSDK.getInstance().addText(1,2,title);
            HJBluetoothPrintSDK.getInstance().addSpliteLine();

            String [] line0={"10","4","4","4","4"};
            List<String> line0List= Arrays.asList(line0);
            String [] line1={"名称","颜色","数量","价格","小记"};
            List<String> line1List= Arrays.asList(line1);
            String [] line2={"大皮球","黄色","1","1","1"};
            List<String> line2List= Arrays.asList(line2);
            String [] line3={"重庆麻辣烫","红白色","100","100","10000"};
            List<String> line3List= Arrays.asList(line3);
            List<List<String>> list = new ArrayList<>();
            list.add(line0List);
            list.add(line1List);
            list.add(line2List);
            list.add(line3List);

            HJBluetoothPrintSDK.getInstance().addTextList(0,0,list);

            HJBluetoothPrintSDK.getInstance().addSpliteLine();

            if(!TextUtils.isEmpty(qrCode)){
//                mBlueToothService.PrintImage(ZiXingUtil.encodeAsBitmap(qrCode),10000);
//                mBlueToothService.printSimple2QRcode(mContext,qrCode);
                HJBluetoothPrintSDK.getInstance().addBarcode(0,0,qrCode);
            }

//            mBlueToothService.printCutPage("");
            HJBluetoothPrintSDK.getInstance().startPrint();
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTime > 2000) {
            Toast.makeText(this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            lastTime = System.currentTimeMillis();
        } else {


            HJBluetoothPrintSDK.getInstance().exitBluetooth();

            super.onBackPressed();
            //关闭程序
//            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
