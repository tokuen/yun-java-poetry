package com.yun.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class FlowLogAspect {

    private static final String LOG_FLOW_STR = "%s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s";

    private static final Logger flowAuditLog = LoggerFactory.getLogger("flow_audit_logger");

    public static void main(String[] args) {
        FlowLogAspect flowLog = new FlowLogAspect();
    }

    @Around("execution(* com..*.service..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();

        Map<String, Object> reqKeyAndValue = new HashMap<>();
        for (Object arg : args) {
            reqKeyAndValue.putAll(getKeyAndValue(arg));
        }

        String requestId =(String) reqKeyAndValue.get("requestId");
        int index = requestId.lastIndexOf(":");
        if(index<0){
            requestId=requestId+":1";
        }else {
            requestId=requestId.substring(0,index)+""+(Integer.valueOf(requestId.substring(index+1,requestId.length()))+1);
        }

        long startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
        Object result = pjp.proceed();//当使用环绕通知时，这个方法必须调用，否则拦截到的方法就不会再执行了
        long endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        String elapseTime = String.valueOf(endTimeMillis - startTimeMillis);

        String classNameAndMethodName = pjp.getTarget().getClass().getCanonicalName().substring(pjp.getTarget().getClass().getCanonicalName().lastIndexOf(".") + 1) + "." + pjp.getSignature().getName();
        String responseBody = getResponseBody(result);
        System.out.println(requestId+" "+"yun-java-poetry"+" "+startTimeMillis+" "+elapseTime+" "+classNameAndMethodName+" "+reqKeyAndValue.toString()+" "+responseBody+" "+0);

        return result;
    }

    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    public static String getResponseBody(Object object) {
        StringBuffer sb = new StringBuffer();
        if (object != null) {
            //获得返回体的响应码
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

}
