package com.example.doulai.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.doulai.hjbtprintersdk.HJBluetoothPrintSDK;
import com.example.doulai.printersdk.R;


/**
 * Created by
 */

public class QRCodeActivity extends BaseActivity {

    private EditText edQRCode;
    private TextView tvSan;
    final int manifestCamera = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        edQRCode = (EditText)findViewById(R.id.ed_qr_url);
        edQRCode.setText(HJSharedPreferencesUitls.getString(mContext, Constants.SP_PRINT_TWO,""));
        tvSan = (TextView)findViewById(R.id.tv_scan);
        tvSan.setOnClickListener(this);
        tvActBarLeft.setOnClickListener(this);
        tvActBarRight.setOnClickListener(this);
    }

    @Override
    protected String getPageTite() {
        return "二维码";
    }

    @Override
    protected String getPageLeft() {
        return "<返回";
    }

    @Override
    protected String getPageRight() {
        return "完成";
    }

    @Override
    public void afterRequestPermisson(int requestCode) {
        if(requestCode == manifestCamera){
//            IntentIntegrator intentIntegrator = new IntentIntegrator(QRCodeActivity.this);
//            intentIntegrator.initiateScan();
        }
    }

    @Override
    public void afterDeniedPermisson(int requestCode) {
        if(requestCode == manifestCamera) {
            Toast.makeText(mContext, "该功能需要赋予访问相机的权限，不开启将无法正常工作！请手动开启权限！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_actbar_left:
                finish();
                break;
            case R.id.tv_actbar_right:
//                HJSharedPreferencesUitls.saveString(mContext,HJConstants.SP_PRINT_TWO,edQRCode.getText().toString());
                finish();
                break;
            case R.id.tv_scan:
//                if(SystemUtils.isOsSixPlus() && !isPermmisonAllowed(Manifest.permission.CAMERA)){
//                    requestPermmison(Manifest.permission.CAMERA,manifestCamera);
//                }else{
//                    IntentIntegrator intentIntegrator = new IntentIntegrator(QRCodeActivity.this);
//                    intentIntegrator.initiateScan();
//                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if (scanResult !=null){
//            String result=scanResult.getContents();
//            edQRCode.setText(result);
//        }
    }
}
