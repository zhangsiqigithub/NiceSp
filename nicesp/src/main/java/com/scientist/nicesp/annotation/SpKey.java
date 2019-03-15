package com.scientist.nicesp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/3/13
 * Time: 4:45 PM
 * Desc: 用来标注接口内的方法，定义sp存储字段的key
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SpKey {
    String spKey();
}
