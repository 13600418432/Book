package com.zqx.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {

    /**
     *功能描述 把所有请求的参数都注入到user对象中
     * @author zqx
     * @date 2021/5/28
      * @param value
     * @param bean
     * @return T
     */
    public static <T> T copyParamToBean(Map value,T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     *功能描述 将字符串转换成为int类型的数据
     * @author zqx
     * @date 2021/5/28
      * @param strInt
     * @param defaultValue
     * @return int
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
