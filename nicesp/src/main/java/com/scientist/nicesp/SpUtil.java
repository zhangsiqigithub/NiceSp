package com.scientist.nicesp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/3/12
 * Time: 11:58 AM
 * Desc: 通用 shared preferences util
 */
public class SpUtil {

    private SharedPreferences mSp;

    public SpUtil(Context context) {
        this(context, context.getPackageName() + "_sp");
    }

    public SpUtil(Context context, String spTableName) {
        mSp = context.getSharedPreferences(spTableName, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        mSp.edit().putString(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    public void putInt(String key, int value) {
        mSp.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    public void putFloat(String key, float value) {
        mSp.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float defValue) {
        return mSp.getFloat(key, defValue);
    }

    public void putLong(String key, long value) {
        mSp.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long defValue) {
        return mSp.getLong(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        mSp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    public void putStringSet(String key, Set<String> value) {
        mSp.edit().putStringSet(key, value).apply();
    }

    public Set<String> getStringSet(String key, Set<String> defValue) {
        return mSp.getStringSet(key, defValue);
    }

    public void clear() {
        mSp.edit().clear().apply();
    }

    public void remove(String key) {
        mSp.edit().remove(key).apply();
    }
}
