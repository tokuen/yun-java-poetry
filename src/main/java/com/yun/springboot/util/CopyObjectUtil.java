package com.yun.springboot.util;

import org.springframework.beans.BeanUtils;

public class CopyObjectUtil {

    public static <T> T copyObj(Class<?> doClass, Class<T> voClass) {
        T voObj = null;
        try {
            voObj = voClass.newInstance();
            BeanUtils.copyProperties(doClass, voObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return voObj;
    }

}
