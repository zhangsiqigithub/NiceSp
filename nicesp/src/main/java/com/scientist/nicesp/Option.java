package com.scientist.nicesp;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/3/13
 * Time: 4:10 PM
 * Desc: Sp接口的返回操作
 */
public interface Option <SpType>{

    SpType get(SpType defValue);

    SpType get();

    void set(SpType value);
}
