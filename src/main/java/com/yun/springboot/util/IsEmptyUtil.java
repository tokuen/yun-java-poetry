package com.yun.springboot.util;

import java.util.Collection;
import java.util.Map;

public class IsEmptyUtil {

    public static boolean isEmpty4Object(Object... objectArr) {
        if (objectArr == null || objectArr.length == 0) {
            return false;
        }
        for (int i = 0; i < objectArr.length; i++) {
            if(objectArr[i]==null){
                return false;
            }

            if (objectArr[i] instanceof String) {
                String str = (String) objectArr[i];
                return str.isEmpty();
            } else if (objectArr[i] instanceof Collection) {
                Collection collection = (Collection) objectArr[i];
                return collection.isEmpty();
            } else if (objectArr[i] instanceof Map) {
                Map map = (Map) objectArr[i];
                return map.isEmpty();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] intArr = null;
        System.out.println(IsEmptyUtil.isEmpty4Object(intArr));;
    }
}