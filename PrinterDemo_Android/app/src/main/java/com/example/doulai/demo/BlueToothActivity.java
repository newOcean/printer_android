package com.example.doulai.demo;

import android.Manifest;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.doulai.hjbtprintersdk.HJBluetoothPrintSDK;
import com.example.doulai.printersdk.R;

import java.util.Set;

/**
 * Author:
 */
public class BlueToothActivity extends BaseActivity {
//    public static HJBlueToothService mBlueToothService = null;
    private Handler mHandler;
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    private Set<BluetoothDevice> devices;
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    private ListView deviceList;
//    private String content;
//    private String title;
//    private Bitmap logo;
    private static boolean print = false;
    private TextView tvSetPrintTemplate;
    private final int requestBlooth = 11;


    private TextView tvLookupPrinter;
//    private String qrCode;
//    private String summarize;
//    private String pageEnd;
    DialogUtils dialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        mContext =this;
        tvActBarLeft.setOnClickListener(this);
        deviceList = (ListView) findViewById(R.id.bluetooth_lv);
        tvSetPrintTemplate = (TextView)findViewById(R.id.tv_print_template);
        tvLookupPrinter = (TextView)findViewById(R.id.tv_lookup_printer);
        tvLookupPrinter.setOnClickListener(this);
        tvSetPrintTemplate.setOnClickListener(this);
        if(Build.VERSION.SDK_INT >=23 && !isPermmisonAllowed(Manifest.permission.BLUETOOTH)){
            requestPermmison(Manifest.permission.BLUETOOTH,requestBlooth);
        }else{
            //模拟器暂时注释
            initBlueTooth();
            HJBluetoothPrintSDK.getInstance().setOnReceive(new HJBluetoothPrintSDK.OnReceiveDataHandleEvent() {
                @Override
                public void OnReceive(BluetoothDevice device) {
                    if (device != null && device.getName() != null && !device.getName().contains("phone")) {
                        mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    }
                }
            });
            Scan();
        }


    }

    @Override
    protected String getPageTite() {
        return "配置打印机";
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
        if(requestCode == requestBlooth) {
            initBlueTooth();
            HJBluetoothPrintSDK.getInstance().setOnReceive(new HJBluetoothPrintSDK.OnReceiveDataHandleEvent() {
                @Override
                public void OnReceive(BluetoothDevice device) {
                    if (device != null && device.getName() != null && !device.getName().contains("phone")) {
                        mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    }
                }
            });
            Scan();
        }
    }

    @Override
    public void afterDeniedPermisson(int requestCode) {
        if(requestCode == requestBlooth) {
            Toast.makeText(mContext, "该功能需要赋予蓝牙的权限，不开启将无法正常工作！请手动开启权限！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {
        super.initData();

//        content = getIntent().getStringExtra("content");
//        title = getIntent().getStringExtra("title");
//        summarize = getIntent().getStringExtra("summarize");
//
//        logo =null;
//        byte [] bis=getIntent().getByteArrayExtra("logo");
//        if(bis!=null && bis.length >0)
//            logo= BitmapFactory.decodeByteArray(bis, 0, bis.length);
//
////        pageEnd = new HJBluetoothPrintSDK().getPageEndContent(mContext);
//        qrCode = HJSharedPreferencesUitls.getString(mContext, HJConstants.SP_PRINT_TWO,"");
        //请求蓝牙权限

    }

    @Override
    protected void onResume() {
        super.onResume();
//        pageEnd = new HJBluetoothPrintSDK().getPageEndContent(mContext);
//        qrCode = HJSharedPreferencesUitls.getString(mContext, HJConstants.SP_PRINT_TWO,"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (HJBluetoothPrintSDK.getInstance().GetScanState() == HJBluetoothPrintSDK.STATE_SCANING) {
            HJBluetoothPrintSDK.getInstance().StopScan();
        }
    }

    private void initBlueTooth() {
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1001:// 连接状态

                        dialogUtils =new DialogUtils(mContext);
                        dialogUtils.showLoadingWithLabel("正在连接...");
//                        dialogUtils.showLoading();
                        break;
                    case MESSAGE_STATE_CHANGE:// 蓝牙连接状态
                        switch (msg.arg1) {
                            case HJBluetoothPrintSDK.STATE_CONNECTED:// 已经连接
                                dialogUtils.dismissLoading();
                                break;
                            case HJBluetoothPrintSDK.STATE_CONNECTING:// 正在连接
                                break;
                            case HJBluetoothPrintSDK.STATE_LISTEN:
                            case HJBluetoothPrintSDK.STATE_NONE:
                                break;
                            case HJBluetoothPrintSDK.SUCCESS_CONNECT:
                                 {
                                     dialogUtils.dismissLoading();
                                     dialogUtils.showAlertDialog("连接成功，请返回打印","确定",null );
//                                    Toast.makeText(BlueToothActivity.this, "连接成功，请返回打印", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case HJBluetoothPrintSDK.FAILED_CONNECT:
                                dialogUtils.dismissLoading();
                                dialogUtils.showAlertDialog("连接失败，请重启打印机或手机app","确定",null );
//
//                                Toast.makeText(BlueToothActivity.this, "连接失败，请重启打印机或手机app", Toast.LENGTH_SHORT).show();

                                break;
                            case HJBluetoothPrintSDK.FAILED_CONNECT_UNREG:
                                dialogUtils.dismissLoading();
                                dialogUtils.showAlertDialog("蓝牙地址未注册，请联系厂家","确定",null );
//
//                                Toast.makeText(BlueToothActivity.this, "蓝牙地址未注册，请联系客服", Toast.LENGTH_SHORT).show();

                                break;

                            case HJBluetoothPrintSDK.LOSE_CONNECT:
                                switch (msg.arg2) {
                                    case -1:
                                        break;
                                    case 0:
                                        break;
                                }
                        }
                        break;
                    case MESSAGE_READ:
                        // sendFlag = false;//缓冲区已满
                        break;
                    case MESSAGE_WRITE:// 缓冲区未满
                        // sendFlag = true;
                        print = true;
//                        mBlueToothService.DisConnected();
                        finish();
                        break;

                }
            }
        };

//        if(mBlueToothService==null)
//            mBlueToothService = new HJBlueToothService(this, mHandler);

        HJBluetoothPrintSDK.getInstance().initContext(this,mHandler);

        mNewDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.item_blooth_device_name);
        deviceList.setAdapter(mNewDevicesArrayAdapter);
        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取蓝牙物理地址
                if (!HJBluetoothPrintSDK.getInstance().IsOpen()) {// 判断蓝牙是否打开
                    HJBluetoothPrintSDK.getInstance().OpenDevice();
                    return;
                }
                if (HJBluetoothPrintSDK.getInstance().GetScanState() == HJBluetoothPrintSDK.STATE_SCANING) {
                    Message msg = new Message();
                    msg.what = 2;
                    mHandler.sendMessage(msg);
                }
                if (HJBluetoothPrintSDK.getInstance().getState() == HJBluetoothPrintSDK.STATE_CONNECTING) {
                    return;
                }
                String info = ((TextView) view).getText().toString();
                String address = info.substring(info.length() - 17);
                String name = info.substring(0,info.length() - 18);//打印机的名字todo 应该用／n
                if (HJBluetoothPrintSDK.getInstance().getState() == HJBluetoothPrintSDK.STATE_CONNECTED)
                    HJBluetoothPrintSDK.getInstance().DisConnected();

//                Toast.makeText(mContext,"正在连接...",Toast.LENGTH_LONG).show();

                //开始菊花
                Message msg = new Message();
                msg.what = 1001;
                mHandler.sendMessage(msg);
                HJBluetoothPrintSDK.getInstance().ConnectToDevice(address);// 连接蓝牙

            }
        });
    }

    private void Scan() {

        if (!HJBluetoothPrintSDK.getInstance().IsOpen()) {
            HJBluetoothPrintSDK.getInstance().OpenDevice();
            return;
        }
        if (HJBluetoothPrintSDK.getInstance().GetScanState() == HJBluetoothPrintSDK.STATE_SCANING) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Scan();
                }
            },1000);
            return;
        }
        mNewDevicesArrayAdapter.clear();
        devices = HJBluetoothPrintSDK.getInstance().GetBondedDevice();

        if (devices.size() > 0) {

            for (BluetoothDevice device : devices) {
                mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
        deviceList.setAdapter(mNewDevicesArrayAdapter);
        new Thread() {
            public void run() {
                if (HJBluetoothPrintSDK.getInstance().GetScanState() != HJBluetoothPrintSDK.STATE_SCANING)
                    HJBluetoothPrintSDK.getInstance().ScanDevice();
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_print_template:
                startActivity(new Intent(mContext, SetPrintTemplateActivity.class));
                break;
            case R.id.tv_actbar_left:
                finish();
                break;
            case R.id.tv_lookup_printer:

                try{
                    HJBluetoothPrintSDK.jumpToBrowser(mContext, "taobao://item.taobao.com/item.htm?id=522782858262&spm=2014.21600712.0.0");
                }catch (Exception e) {
                    HJBluetoothPrintSDK.jumpToBrowser(mContext, "https://item.taobao.com/item.htm?id=522782858262&spm=2014.21600712.0.0");
                }
                break;
            default:
                break;
        }
    }
}
