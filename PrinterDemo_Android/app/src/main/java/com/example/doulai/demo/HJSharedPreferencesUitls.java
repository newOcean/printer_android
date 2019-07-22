package com.example.doulai.demo;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * sp工具类
 *
 * @author xiehai
 */
public class HJSharedPreferencesUitls {
    public static String SP_NAME = "config";
    private static SharedPreferences sp;

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        //如果是密码

        sp.edit().putString(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }


        try{
            return sp.getString(key,defValue);
        }catch (Exception e){
            return defValue;
        }
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putInt(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putLong(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void savefloat(Context context, String key, float value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * @param context
     */
    public static void cleanAll(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().clear().commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getInt(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context, String key, long defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getLong(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static float getfloat(Context context, String key, float defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getFloat(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getBoolean(key, defValue);
    }

    public static boolean getIsSavePrint(Context context){
        return getBoolean(context, Constants.SP_IS_SAVE_PRINT,false);
    }

    public static void setIsSavePrint(Context context , boolean enable){
        saveBoolean(context,Constants.SP_IS_SAVE_PRINT,enable);
    }

    public static boolean getIsPrintCustom(Context context){
        return getBoolean(context, Constants.SP_IS_PRINT_CUSTOM,false);
    }

    public static void setIsPrintCustom(Context context , boolean enable){
        saveBoolean(context,Constants.SP_IS_PRINT_CUSTOM,enable);
    }
}
