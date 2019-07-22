package com.example.doulai.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.doulai.printersdk.*;
import com.example.doulai.hjbtprintersdk.HJBluetoothPrintSDK;
import com.example.doulai.printersdk.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import static android.webkit.WebView.enableSlowWholeDocumentDraw;


/**
 * Created by
 */

public class PrintPreViewActivity extends BaseActivity {
    TextView htmlShare;
    TextView photoShare;
    TextView remoteShare;
    WebView previewWebView;

    private String content;
    private String title;
    private Bitmap logo;
    private String qrCode;
    private String summarize;
    private String pageEnd;

    String htmlcontent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            if (Build.VERSION.SDK_INT >= 21)
                enableSlowWholeDocumentDraw();

        setContentView(R.layout.activity_print_preview);
        Log.e("","onCreate");
        tvActBarLeft.setOnClickListener(this);
        tvActBarRight.setOnClickListener(this);
        htmlShare = (TextView) findViewById(R.id.tv_html);
        photoShare = (TextView) findViewById(R.id.tv_phtoto);
        remoteShare  = (TextView) findViewById(R.id.tv_remote_printer);
        htmlShare.setOnClickListener(this);
        photoShare.setOnClickListener(this);
        remoteShare.setOnClickListener(this);
        previewWebView =(WebView) findViewById(R.id.print_preview);

        htmlcontent= createHtml();
//        if(previewWebView!=null)
        previewWebView.loadData(htmlcontent, "text/html; charset=UTF-8", null);

    }
    @Override
    protected String getPageTite() {
        return "打印预览";
    }

    @Override
    protected String getPageLeft() {
        return  "<订单";
    }

    @Override
    protected String getPageRight() {
        return "蓝牙打印";
    }

    @Override
    public void afterRequestPermisson(int requestCode) {

    }

    @Override
    public void afterDeniedPermisson(int requestCode) {

    }
    @Override
    protected void initData() {
        super.initData();
        Log.e("","initdata");
//        previewWebView =(WebView) findViewById(R.id.print_preview);

//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        content = getIntent().getStringExtra("content");
        title = getIntent().getStringExtra("title");
        summarize = getIntent().getStringExtra("summarize");

        logo =null;
        byte [] bis=getIntent().getByteArrayExtra("logo");
        if(bis!=null && bis.length >0)
            logo= BitmapFactory.decodeByteArray(bis, 0, bis.length);

//        pageEnd = new HJBluetoothPrintSDK().getPageEndContent(mContext);
        qrCode = HJSharedPreferencesUitls.getString(mContext, Constants.SP_PRINT_TWO,"");
        //请求蓝牙权限


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_actbar_left:
                finish();
                break;
            case R.id.tv_actbar_right:
                bluetoothPrint();
                break;
            case R.id.tv_html:
                shareWeb();
                break;
            case R.id.tv_phtoto:
                shareWx();
                break;
            case R.id.tv_remote_printer:
                bluetoothPrint();
                break;
            default:
                break;
        }
    }
    /**
     * 截屏
     * @param
     * @return
     */
//    private Bitmap captureScreen() {
//        View cv = this.getWindow().getDecorView();
//        Bitmap bmp = Bitmap.createBitmap(cv.getWidth(), cv.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bmp);
//        cv.draw(canvas);
//        return bmp;
//    }
    /**
     * 截取webView快照(webView加载的整个内容的大小)
     * @param
     * @return
     */
    private Bitmap captureWebView(){
//        Picture snapShot = previewWebView.capturePicture();
//
//        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),snapShot.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bmp);
//        snapShot.draw(canvas);

//        View view  = this.getWindow().getDecorView();
//        Bitmap bmp = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bmp);
//        view.draw(canvas);

        //获取webview缩放率
        float scale = previewWebView.getScale();
//得到缩放后webview内容的高度
        int webViewHeight = (int) (previewWebView.getContentHeight()*scale);
        Bitmap bmp = Bitmap.createBitmap(previewWebView.getWidth(),webViewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
//绘制
        previewWebView.draw(canvas);

        return bmp;
    }
    public static void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        return dir;

    }
    // 将字符串写入到文本文件中
    public void writeTxtToFile(String strcontent, File file) {
        //生成文件夹之后，再生成文件，不然会出错
//        makeFilePath(filePath, fileName);

//        String strFilePath = filePath+fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
//            File file = new File(strFilePath);
            file.delete();
            if (!file.exists()) {
//                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");

            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }
    private void shareWeb() {
        File fileDir = new File(getSDPath() + "");
        makeDir(fileDir);
        File avaterFile = new File(getSDPath(), "/zskddingdan.html");//设置文件名称

        writeTxtToFile(htmlcontent,avaterFile);

        HJFileToolUtil.shareWeb(this,avaterFile,"application/pdf");

    }


    private static String insertImageToSystem(Context context, String imagePath) {
        String url = "";
        try {
            url = MediaStore.Images.Media.insertImage(context.getContentResolver(), imagePath, "crop.jpg", "图片");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return url;
    }
    private void shareWx(){
        Bitmap image =captureWebView();
        String urlpath = HJFileToolUtil.saveFile(this, "crop.jpg", image);
      //  Log.e("x","path=="+urlpath);

        String imageUriSys = insertImageToSystem(this, urlpath);
        HJFileToolUtil.shareImge(this,imageUriSys,"image/jpg");

    }
//    private void openFile(File file){
//
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////设置intent的Action属性
//        intent.setAction(Intent.ACTION_VIEW);
////获取文件file的MIME类型
////        String type = getMIMEType(file);
//        String type = "image/jpg";
////设置intent的data和Type属性。
//        intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
////跳转
//        startActivity(intent); //这里最好try一下，有可能会报错。 //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
//
//    }
    private String createHtml(){
        String linkCss = "<style type=\"text/css\"> " +
                "img {" +
                "width:100%;" +
                "height:auto;" +
                "}" +
                "body {" +
                "margin-right:15px;" +
                "margin-left:15px;" +
                "margin-top:15px;" +
                "font-size:20px;" +
                "charset=UTF8;" +
                "}" +
                "</style>";
        String replacedTitle=new String(title);
        String replacedContent =new String(content);
        String replacedSumarize =new String(summarize);
        String replacedPageEnd = new String(pageEnd);
        replacedTitle =replacedTitle.replace("\n","<br>");
        replacedContent=replacedContent.replace("\n","<br>");
        replacedSumarize=replacedSumarize.replace("\n","<br>");
        replacedPageEnd =replacedPageEnd.replace("\n","<br>");
        replacedTitle =replacedTitle.replace(" ","&nbsp;");
        replacedContent =replacedContent.replace(" ","&nbsp;");
        replacedSumarize =replacedSumarize.replace(" ","&nbsp;");
        replacedPageEnd =replacedPageEnd.replace(" ","&nbsp;");

        String titleHtml="<h1 align=center>"+replacedTitle+"</h1>";

        String body =titleHtml +replacedContent+replacedSumarize+replacedPageEnd+"<br>";
linkCss="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF8\">";
                String html = "<html><header> " + linkCss + "</header>" +"<body align=left >"+ body + "</body></html>";
  return html;


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


        if( (!HJBluetoothPrintSDK.getInstance().IsOpen())
                || (HJBluetoothPrintSDK.getInstance().getState() != HJBluetoothPrintSDK.STATE_CONNECTED)) {
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
        }
        else{
            if(logo!=null)
            {
                HJBluetoothPrintSDK.getInstance().addImage(1,0,logo);
            }
             {
                 HJBluetoothPrintSDK.getInstance().addText(1,2,title);
                 HJBluetoothPrintSDK.getInstance().addText(0,0,content+summarize);
                 HJBluetoothPrintSDK.getInstance().addText(0,0,pageEnd);
            }
            if(!TextUtils.isEmpty(qrCode)){

                HJBluetoothPrintSDK.getInstance().addBarcode(0,0,qrCode);
            }

            HJBluetoothPrintSDK.getInstance().addCutPage();
            HJBluetoothPrintSDK.getInstance().startPrint();
        }
    }
}
