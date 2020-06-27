package com.yun.springboot.util;

import com.yun.springboot.model.result.RetResult;
import org.slf4j.Logger;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 请求工具类
 *
 * @author yue
 */
public class LogUtils {

    /**
     * 获取请求的真实IP
     *
     * @param request
     * @return
     */
    public static String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String[] split = ip.split(",");
        if (split.length > 0) {
            ip = split[0];
        }
        return ip;
    }

    /**
     * 获取请求的代理IP
     *
     * @param request
     * @return
     */
    public static String getProxyIP(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    /**
     * 封装请求体
     *
     * @return
     */
    public static String getRequestBody(Map<String, String[]> map, HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        if (!map.isEmpty()) {
            for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
                String paramName = iterator.next();
                String paramValue = req.getParameter(paramName);
                if (LogUtils.isEmpty(paramName) || LogUtils.isEmpty(paramValue)) {
                } else {
                    //每一个键值对长度太长时截取部分
                    if (paramValue.length() > 1000) {
                        sb.append(paramName).append(":").append(paramValue.substring(0, 1000)).append("***^_^");
                    } else {
                        sb.append(paramName).append(":").append(paramValue).append("^_^");
                    }
                    //整个消息太长的话停止截取
                    if (sb.length() > 10000) {
                        sb.append("aopMes").append(":").append("消息体太长已截取").append("^_^");
                        break;
                    }
                }
            }
            if (sb.length() > 3) {
                return sb.substring(0, sb.length() - 3);
            }
        }
        return sb.toString();
    }


    /**
     * 封装响应体
     *
     * @return
     */
    public static String getResponseBody(Object object) {
        StringBuffer sb = new StringBuffer();
        //获得返回体的响应码
        RetResult retResult = (RetResult) object;
        int resultCode = retResult.getCode();
        String message = retResult.getMessage();
        Object data = retResult.getData();
        sb.append("return_code").append(":").append(resultCode).append("^_^");
        sb.append("return_message").append(":").append(message).append("^_^");
        sb.append(LogUtils.object4String(data));
        return sb.toString();
    }

    public static int getResultCode(Object object) {
        //获得返回体的响应码
        RetResult retResult = (RetResult) object;
        return retResult.getCode();
    }

    /**
     * boolean
     * 根据请求的类型是否在数组里面是否拦截请求
     */
    public static boolean checkContentType(String contentType, String[] contentTypes) {
        boolean filterFlag = false;
        for (String p : contentTypes) {
            if (LogUtils.contains(contentType, p)) {
                filterFlag = true;
            }
        }
        //contentType可能是空
        if (LogUtils.isEmpty(contentType)) {
            filterFlag = true;
        }
        return filterFlag;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    public static String getRequestJson(Object... args) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(LogUtils.object4String(args[i]));
        }
        return sb.toString();
    }

    public static String getRespJson(Object... args) {
        Object arg = args[args.length - 1];
        StringBuffer sb = new StringBuffer();
        if (arg == null) {
            return null;
        } else {
            sb.append(LogUtils.object4String(arg));
        }
        return sb.toString();
    }

    public static String getNewReqId(String requestId) {
        int index = requestId.lastIndexOf(":");
        if (index < 0) {
            requestId = requestId + ":1";
        } else {
            requestId = requestId.substring(0, index) + ":" + (Integer.valueOf(requestId.substring(index + 1)) + 1);
        }
        return requestId;
    }

    public static void printLog(Logger logger, String logString) {
        logger.info(logString);
    }

    //没有参数的key的方式
    public static String object4String(Object object) {
        String typeName = object.getClass().getTypeName();
        StringBuffer sb = new StringBuffer();
        if (MyUtil.isEmpty4Object(object)) {
            sb.append(typeName.substring(typeName.lastIndexOf(".") + 1)).append(":").append("^_^");
        } else if (object instanceof Map) {
            Map map = (Map) object;
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                sb.append("key:").append(key).append(";value:").append(value);
            }
            sb.append("^_^");
        } else if (object instanceof Collection) {
            Collection collection = (Collection) object;
            Iterator<Object> iter = collection.iterator();
            while (iter.hasNext()) {
                Object next = iter.next();
                sb.append(next);
            }
            sb.append("^_^");
        } else {
            //遍历通过反射获取object的类中的属性名
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);    //设置改变属性为可访问
                try {
                    Object value = f.get(object);
                    if (value != null) {
                        sb.append(f.getName()).append(":").append(value).append("^_^");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (sb.length() > 3) {
            return sb.substring(0, sb.length() - 3);
        } else {
            return sb.toString();
        }
    }


    public static String getRequestJson(String[] parameterNames, Object[] objects) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals("requestId")){
                continue;
            } else if (LogUtils.isBaseType(objects[i])){
                sb.append(parameterNames[i]).append(":").append(objects[i]).append("^_^");
            }else {
                sb.append(object4String(objects[i])).append("^_^");
            }
        }
        if (sb.length() > 3) {
            return sb.substring(0, sb.length() - 3);
        } else {
            return sb.toString();
        }
    }

    public static boolean isBaseType(Object object) {
        if (object instanceof String || object instanceof StringBuffer || object instanceof StringBuilder) {
            return true;
        }
        if (object instanceof Byte) {
            return true;
        }
        if (object instanceof Short) {
            return true;
        }
        if (object instanceof Integer || object instanceof AtomicInteger) {
            return true;
        }
        if (object instanceof Float) {
            return true;
        }
        if (object instanceof Double) {
            return true;
        }
        if (object instanceof Float) {
            return true;
        }
        if (object instanceof Long || object instanceof AtomicLong) {
            return true;
        }
        if (object instanceof Character) {
            return true;
        }
        if (object instanceof Boolean || object instanceof AtomicBoolean) {
            return true;
        }
        return false;
    }

    public static String getResponseService(Object object) {
        StringBuffer sb = new StringBuffer();
        sb.append(LogUtils.object4String(object));
        return sb.toString();
    }
}
