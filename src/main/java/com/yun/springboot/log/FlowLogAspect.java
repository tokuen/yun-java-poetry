package com.yun.springboot.log;

import com.yun.springboot.util.LogUtils;
import com.yun.springboot.util.MyUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Aspect
@Component
public class FlowLogAspect {

    private static final String LOG_FLOW_STR = "%s,  %s,  %s,  %s,  %s,  %s,  %s";
    private static final Logger flow_audit_logger = LoggerFactory.getLogger("flow_audit_logger");
    //时间格式
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Around("execution(* com..*.service..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        String className = target.getClass().getName();
        String classNameMin = className.substring(className.lastIndexOf(".") + 1);
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的放参数类型
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        int requestIdIndex = MyUtil.indexOf(parameterNames, "requestId");
        // 拦截的方法参数
        Object[] args = pjp.getArgs();


        String reqId =(String) args[requestIdIndex];
        //项目名称
        String serviceId = "yun-java-poetry";
        long startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
        String receiveTime = df.format(startTimeMillis);//RECEIVE_TIME 收到请求的的时间
        Object result = pjp.proceed();//当使用环绕通知时，这个方法必须调用，否则拦截到的方法就不会再执行了
        long endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        String elapseTime = String.valueOf(endTimeMillis - startTimeMillis);

        //DUMMY 类名.方法名称
        String dummy =classNameMin + "." + methodName;
        String reqStrJson = LogUtils.getRequestJson(parameterNames,args);

        String respStrJson = LogUtils.getResponseService(result);
        String flowLogStr = String.format(LOG_FLOW_STR,reqId,serviceId,receiveTime,elapseTime,dummy,reqStrJson,respStrJson);
        LogUtils.printLog(flow_audit_logger, flowLogStr);

        return result;
    }
}
