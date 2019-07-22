package com.example.doulai.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by CMCC-ZHENGCHENG on 2017/4/11.
 */

public class HJFileToolUtil {
    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录，是linux下的目录形式,如/photo/
     * @param filename
     *            上传到FTP服务器上的文件名,是自己定义的名字，
     * @param input
     *            输入流
     * @return 成功返回true，否则返回false
     */
//    public static boolean uploadFile(String url, int port, String username,
//                                     String password, String path, String filename, InputStream input) {
//        boolean success = false;
//        FTPClient ftp = new FTPClient();
//
//
//
//
//        try {
//            int reply;
//            ftp.connect(url, port);// 连接FTP服务器
//            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//            ftp.login(username, password);//登录
//            reply = ftp.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftp.disconnect();
//                return success;
//            }
//            ftp.setFileType(FTP.BINARY_FILE_TYPE);
//            ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
//            ftp.changeWorkingDirectory(path);
//            ftp.storeFile(filename, input);
//
//            input.close();
//            ftp.logout();
//            success = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (ftp.isConnected()) {
//                try {
//                    ftp.disconnect();
//                } catch (IOException ioe) {
//                }
//            }
//        }
//        return success;
//    }

    /**
     * 将Bitmap 图片保存到本地路径，并返回路径
     *
     * @param fileName 文件名称
     * @param bitmap   图片
     * @param资源类型，参照 MultimediaContentType 枚举，根据此类型，保存时可自动归类
     */
    public static String saveFile(Context c, String fileName, Bitmap bitmap) {
        return saveFile(c, "", fileName, bitmap);
    }

//    public static String saveFile(Context c, String filePath, String fileName, Bitmap bitmap) {
////        byte[] bytes = bitmapToBytes(bitmap);
//        return saveFile(c, filePath, fileName, bitmap);
//    }

    /**
     * Bitmap 转 字节数组
     *
     * @param
     * @return
     */
//    public static byte[] bitmapToBytes(Bitmap bm) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        return baos.toByteArray();
//    }

    public static String saveFile(Context c, String filePath, String fileName, Bitmap bytes) {
        String fileFullName = "";
        FileOutputStream fos = null;
//        String dateFolder = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA)
//                .format(new Date());
        try {
            String suffix = "";
            if (filePath == null || filePath.trim().length() == 0) {
//                filePath = Environment.getExternalStorageDirectory() + "/cxs/" + dateFolder + "/";
                filePath = Environment.getExternalStorageDirectory() +  "/";
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File fullFile = new File(filePath, fileName + suffix);
            fileFullName = fullFile.getPath();
            fos = new FileOutputStream(new File(filePath, fileName + suffix));
//            fos.write(bytes);
//            FileOutputStream fos = new FileOutputStream(avaterFile);
            bytes.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            fileFullName = "";
            e.printStackTrace();

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    fileFullName = "";
                    e.printStackTrace();
                }
            }
        }
        return fileFullName;
    }
    //type "application/pdf"
    public static void shareWeb(Context c, File sharedFile,String type) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.addCategory("android.intent.category.DEFAULT");
        Uri pdfUri;
        pdfUri = Uri.fromFile(sharedFile);
        intent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        intent.setType(type);

        try {
            c.startActivity(Intent.createChooser(intent, sharedFile.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void shareImge(Context c, String sharedFile,String type){
                Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setAction(Intent.ACTION_VIEW);

        intent.setDataAndType(/*uri*/Uri.parse(sharedFile), type);
//跳转
        c.startActivity(intent);
    }
    // 测试
//    public static void main(String[] args) {
//
//        FileInputStream in = null ;
//        File dir = new File("G://pathnew");
//        File files[] = dir.listFiles();
//        if(dir.isDirectory()) {
//            for(int i=0;i<files.length;i++) {
//                try {
//                    in = new FileInputStream(files[i]);
//                    boolean flag = uploadFile("17.8.119.77", 21, "android", "android",
//                            "/photo/", "412424123412341234_20130715120334_" + i + ".jpg", in);
//                    System.out.println(flag);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
}

