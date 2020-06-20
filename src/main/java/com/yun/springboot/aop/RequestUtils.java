package com.yun.springboot.aop;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * 请求工具类
 *
 * @author yue
 */
public class RequestUtils {

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
                String paramName = (String) iterator.next();
                String paramValue = req.getParameter(paramName);
                if (RequestUtils.isEmpty(paramName) || RequestUtils.isEmpty(paramValue) ) {
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
        if (object != null) {
            //获得返回体的响应码
            String resultCode = RequestUtils.getResultCode(object);
            if (object instanceof String) {//string类型的返回类型
                return object.toString();
            } else if (object instanceof Map) {//map的返回类型
                Map<?, ?> map = (Map<?, ?>) object;
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (entry.getValue() != null) {
                        String paramValue = entry.getValue().toString();
                        if (paramValue.length() > 1000) {
                            sb.append(entry.getKey()).append(":").append(paramValue.substring(0, 1000)).append("***^_^");
                        } else {
                            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("^_^");
                        }
                    }
                    //整个消息太长的话停止截取
                    if (sb.length() > 10000) {
                        //将返回码加上
                        if (!RequestUtils.isEmpty(resultCode)) {
                            sb.append("result_code").append(":").append(resultCode).append("^_^");
                        } else {
                            sb.append("result_code").append(":").append("0").append("^_^");
                        }
                        //提示信息
                        sb.append("aopMes").append(":").append("消息体太长已截取！").append("^_^");
                        break;
                    }
                }
            } else if (object instanceof Object) {
                for (Field f : object.getClass().getDeclaredFields()) {   //遍历通过反射获取object的类中的属性名
                    f.setAccessible(true);    //设置改变属性为可访问
                    try {
                        Object value = null;
                        value = f.get(object);
                        if (value != null) {
                            sb.append(f.getName()).append(":").append(value.toString()).append("^_^");
                        }
                        //整个消息太长的话停止截取
                        if (sb.length() > 10000) {
                            //将返回码加上
                            if (!RequestUtils.isEmpty(resultCode)) {
                                sb.append("result_code").append(":").append(resultCode).append("^_^");
                            } else {
                                sb.append("result_code").append(":").append("0").append("^_^");
                            }
                            //提示信息
                            sb.append("aopMes").append(":").append("消息体太长已截取！").append("^_^");
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                return sb.append("aopMes").append(":").append("返回值为未知类型，未对返回值处理！").toString();
            }
        }
        if (sb.length() > 3) {
            return sb.substring(0, sb.length() - 3);
        }
        return sb.toString();
    }

    //获得响应码
    public static String getResultCode(Object object) {
        String result = "200";
        if (object != null) {
            if (object instanceof String) {
                String values = (String) object;
                if (!RequestUtils.isEmpty(values)) {
                    if (values.contains("result_code")) {
                        String s = values.replaceAll("(.*)\"result_code\"\\s*:(\\s*\\-*\\d+)(.*)", "$2");
                        if (!RequestUtils.isEmpty(s) && !s.equals(values)) {
                            return s.trim();
                        }
                    } else if (values.contains("return_code")) {
                        String s = values.replaceAll("(.*)\"return_code\"\\s*:(\\s*\\-*\\d+)(.*)", "$2");
                        if (!RequestUtils.isEmpty(s) && !s.equals(values)) {
                            return s.trim();
                        }
                    }
                }
            } else if (object instanceof Map) {
                Map<String, String> values = (Map<String, String>) object;
                //result_code可能为int和string
                if (String.valueOf(values.get("result_code")).equals("0")) {
                    return String.valueOf(values.get("result_code"));
                }
                if (String.valueOf(values.get("return_code")).equals("0")) {
                    return String.valueOf(values.get("return_code"));
                }
            } else if (object instanceof Object) {
                for (Field f : object.getClass().getDeclaredFields()) {   //遍历通过反射获取object的类中的属性名
                    f.setAccessible(true);    //设置改变属性为可访问
                    try {
                        if (f.getName().equals("result_code")) {
                            Object value = null;
                            value = f.get(object);
                            if (value != null) {
                                return value.toString();
                            }
                        } else if (f.getName().equals("return_code")) {
                            Object value = null;
                            value = f.get(object);
                            if (value != null) {
                                return value.toString();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * boolean
     * 根据请求的类型是否在数组里面是否拦截请求
     */
    public static boolean checkContentType(String contentType, String[] contentTypes) {
        boolean filterFlag = false;
        for (String p : contentTypes) {
            if (RequestUtils.contains(contentType, p)) {
                filterFlag = true;
            }
        }
        //contentType可能是空
        if (RequestUtils.isEmpty(contentType)) {
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

}
