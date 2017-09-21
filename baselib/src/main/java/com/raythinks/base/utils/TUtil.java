package com.raythinks.base.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型实例化工具类 (MVP模式)
 * Created by baixiaokang on 16/4/30.
 */
public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) getClassType(o, i))
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Class<T> getClass(Object o, int i) {
        return ((Class<T>) getClassType(o, i));
    }

    /**
     * 获取含参数的Constructor对象
     *
     * @param o
     * @param i
     * @param paramsType
     * @param <T>
     * @return
     */
    public static <T> Constructor getConstructor(Object o, int i, Class... paramsType) {
        Constructor c = null;
        try {
            c = ((Class<T>) getClassType(o, i)).getConstructor(paramsType);
            return c;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构造对象，新建对象
     *
     * @param c
     * @param paramsValue
     * @param <T>
     * @return
     */
    public static <T> T getT(Constructor c, Object... paramsValue) {
        try {
            return (T) c.newInstance(paramsValue);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Type getClassType(Object o, int i) {
        return ((ParameterizedType) (o.getClass()
                .getGenericSuperclass())).getActualTypeArguments()[i];
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
