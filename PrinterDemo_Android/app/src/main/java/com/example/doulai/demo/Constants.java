package com.example.doulai.demo;



/**
 * Created by
 */

public class Constants {
    //成功
    public static final String SUCCESS = "success";



    //草稿订单技术
    public static final String SP_ORDER_DRAFT = "sp_order_draft";
    public static final String QQ = "mqq://im/chat?chat_type=wpa&uin=40255986&version=1&src_type=web";
    public static final String PHONE = "15988879319";






    //SharePrenferen打印
    public static final String SP_IS_SAVE_PRINT = "is_save_print";
    public static final String SP_IS_PRINT_CUSTOM = "is_print_custom";
    public static final String SP_PRINT_PAGE_START = "print_page_start";
    public static final String SP_PRINT_PAGE_END = "print_page_end";
    public static final String SP_PRINT_TWO = "print_two";
    public static final String DEFAUL_PAGE_START = "欢迎光临";
    public static final String DEFAUL_PAGE_END = "谢谢惠顾";



    //打印各类属性
    public static final String PRINT_PRO_ID = "款号";
    public static final String PRINT_PRO_NAME = "名称";
    public static final String PRINT_PRO_BARCODE = "条码";
    public static final String PRINT_PRO_EXTRA_ONE = "颜色";
    public static final String PRINT_PRO_EXTRA_TWO = "尺码";
    public static final String PRINT_PRO_GUIGE = "规格";
    public static final String PRINT_PRO_COUNT = "数量";
    public static final String PRINT_PRO_PRICE = "原价";
    public static final String PRINT_PRO_SELL_PRICE = "价格";
    public static final String PRINT_PRO_TOTAL = "小计";

    //默认属性
    public static final String[]  DEFAULT_PROPERTY = {Constants.PRINT_PRO_ID,Constants.PRINT_PRO_NAME,Constants.PRINT_PRO_BARCODE,Constants.PRINT_PRO_EXTRA_ONE,Constants.PRINT_PRO_EXTRA_TWO,Constants.PRINT_PRO_GUIGE
            ,Constants.PRINT_PRO_COUNT,Constants.PRINT_PRO_PRICE,Constants.PRINT_PRO_SELL_PRICE,Constants.PRINT_PRO_TOTAL};
    public static boolean[]  DEFAULT_PRO_ENABLE = {true,true,false,true,false,false,true,false,true,true};


    /////4种类型的打印机长度
    public static final String[] PRINT_LENTH_SHOW = {"58mm","80mm","110mm","210mm"};

    ////字体
    public static final String[] PRINT_FONT_SHOW = {"自动","小字体","中字体", "大字体"};

    ////长度key
    public static final String SP_PRINT_MECHINE = "sp_print_lenth";
    ////字体key
    public static final String SP_PRINT_FONT = "sp_print_font";


}
