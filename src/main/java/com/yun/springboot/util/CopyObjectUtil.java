package com.yun.springboot.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

public class CopyObjectUtil {

    public static void copyObj(Object doObject, Object voObject) {
        try {
            BeanUtils.copyProperties(doObject, voObject);
        } catch (BeansException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
