package com.scientist.nicesp;

import android.content.Context;

import com.scientist.nicesp.annotation.SpKey;
import com.scientist.nicesp.annotation.SpTable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/3/13
 * Time: 3:33 PM
 * Desc: sp操作的管理者
 */
public class SpManager {

    public static void clearSpTable(Context context, String tableName) {
        new SpUtil(context, tableName).clear();
    }

    public static <T> void clearSpTable(Context context, Class<T> spInterface) {
        clearSpTable(context, getTableNameFromInterface(spInterface));
    }

    public static void remove(Context context, String tableName, String key) {
        new SpUtil(context, tableName).remove(key);
    }

    public static <T> void remove(Context context, Class<T> spInterface, String key) {
        remove(context, getTableNameFromInterface(spInterface), key);
    }


    /**
     * 获取 SpTable interface 的实例
     * @param context context
     * @param spInterface spInterface 接口 class
     * @param <T> 返回实例
     */
    public static  <T> T provideInstance(Context context, Class<T> spInterface) {
        if (!spInterface.isInterface()) {
            throw new IllegalStateException("spInterface must be an interface");
        }

        String spTableName = getTableNameFromInterface(spInterface);

        final SpUtil spUtil = new SpUtil(context, spTableName);

        @SuppressWarnings("unchecked")
        T instance = (T) Proxy.newProxyInstance(spInterface.getClassLoader(), new Class<?>[]{spInterface}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getReturnType() != Option.class) {
                    throw new IllegalStateException("spInterface method return type must be Option.class but now it is " + method.getReturnType().getName());
                }

                final String spKey;
                SpKey keyAnn = method.getAnnotation(SpKey.class);
                if (keyAnn != null && !keyAnn.spKey().isEmpty()) {
                    spKey = keyAnn.spKey();
                } else {
                    spKey = method.getName();
                }

                ParameterizedType optionType =  ((ParameterizedType) method.getGenericReturnType());

                Type optionGenericType = optionType.getActualTypeArguments()[0];

                if (!(optionGenericType instanceof Class)) {
                    if (optionGenericType.toString().equals("java.util.Set<java.lang.String>")) { //Set<String>类型
                        return new Option<Set<String>>() {
                            @Override
                            public Set<String> get(Set<String> defValue) {
                                return spUtil.getStringSet(spKey, defValue);
                            }

                            @Override
                            public Set<String> get() {
                                return spUtil.getStringSet(spKey, Collections.<String>emptySet());
                            }

                            @Override
                            public void set(Set<String> value) {
                                spUtil.putStringSet(spKey, value);
                            }
                        };
                    }
                    throw new IllegalStateException("the saving type " + optionGenericType + " is not Shared preferences supported");
                }

                Class optionGenericClass = (Class)optionGenericType;

                if (optionGenericClass == String.class) {
                    return new Option<String>() {
                        @Override
                        public String get(String defValue) {
                            return spUtil.getString(spKey, defValue);
                        }

                        @Override
                        public String get() {
                            return spUtil.getString(spKey, "");
                        }

                        @Override
                        public void set(String value) {
                            spUtil.putString(spKey, value);
                        }
                    };
                } else if (optionGenericClass == Integer.class) {
                    return new Option<Integer>() {
                        @Override
                        public Integer get(Integer defValue) {
                            return spUtil.getInt(spKey, defValue);
                        }

                        @Override
                        public Integer get() {
                            return spUtil.getInt(spKey, 0);
                        }

                        @Override
                        public void set(Integer value) {
                            spUtil.putInt(spKey, value);
                        }
                    };
                } else if (optionGenericClass == Long.class) {
                    return new Option<Long>() {
                        @Override
                        public Long get(Long defValue) {
                            return spUtil.getLong(spKey, defValue);
                        }

                        @Override
                        public Long get() {
                            return spUtil.getLong(spKey, 0);
                        }

                        @Override
                        public void set(Long value) {
                            spUtil.putLong(spKey, value);
                        }
                    };
                } else if (optionGenericClass == Float.class) {
                    return new Option<Float>() {
                        @Override
                        public Float get(Float defValue) {
                            return spUtil.getFloat(spKey, defValue);
                        }

                        @Override
                        public Float get() {
                            return spUtil.getFloat(spKey, 0);
                        }

                        @Override
                        public void set(Float value) {
                            spUtil.putFloat(spKey, value);
                        }
                    };
                } else if (optionGenericClass == Boolean.class) {
                    return new Option<Boolean>() {
                        @Override
                        public Boolean get(Boolean defValue) {
                            return spUtil.getBoolean(spKey, defValue);
                        }

                        @Override
                        public Boolean get() {
                            return spUtil.getBoolean(spKey, false);
                        }

                        @Override
                        public void set(Boolean value) {
                            spUtil.putBoolean(spKey, value);
                        }
                    };
                } else {
                    throw new IllegalStateException("the saving type " + optionGenericClass + " is not Shared preferences supported");
                }
            }
        });

        return instance;
    }

    private static <T> String getTableNameFromInterface(Class<T> spInterface) {
        SpTable tableAnn = spInterface.getAnnotation(SpTable.class);
        if (tableAnn == null) {
            throw new IllegalStateException("spInterface must have a SpTable annotation");
        }

        String spTableName = tableAnn.tableName();
        if (spTableName.isEmpty()) {
            spTableName = spInterface.getSimpleName();
        }
        return spTableName;
    }

}
