package com.scientist.nicesp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/3/13
 * Time: 3:47 PM
 * Desc: 用来标注sharedPreference接口, tableName 为表名
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SpTable {
    String tableName() default "";
}
