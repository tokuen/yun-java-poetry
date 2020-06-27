package com.yun.springboot.log;

import com.yun.springboot.util.LogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Map;

@Aspect
@Component
public class RequestLogAspect {

    private static final String LOG_REQUEST_STR = "%s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s";

    private static final Logger requestLogger = LoggerFactory.getLogger("request_audit_logger");

    //时间格式
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    //拦截以下请求类型的消息体
    private static String[] contentTypes = {"application/x-www-form-urlencoded", "application/json", "text/xml", "text/html"};

    @Around("execution(* com..*.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String contentType = request.getContentType();

        //是否拦截并包装请求，如果需要拦截则会获取RequestBody
        boolean filterRequestFlag = LogUtils.checkContentType(contentType, contentTypes);
        // 其他类型的请求不拦截消息体
        Map<String, String[]> inputParamMap = null;
        if (filterRequestFlag) {
            inputParamMap = request.getParameterMap();
        }

        long startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
        Object result = pjp.proceed();//当使用环绕通知时，这个方法必须调用，否则拦截到的方法就不会再执行了
        long endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
//        PROXY_IP 代理IP:代理端口
        String proxyIp = LogUtils.getProxyIP(request).replaceAll(":",".")+
                ":"+request.getRemotePort();
//        CLIENT_IP 客户端IP
        String clientIp = LogUtils.getRealIP(request).replaceAll(":",".")+
                request.getRemotePort();
//        SOCID  客户端标识url
        String socId = "";
        if (LogUtils.isEmpty(request.getRequestURI())) {
            socId = request.getQueryString();
        } else {
            socId = request.getRequestURI();
        }
//        REQUEST_ID 请求ID
        String requestId =(String) request.getAttribute("requestId");
//        SERVICEID  项目名称
        String serviceId = "yun-java-poetry";
//        SERVER_IP 服务端IP
        String serverIp = request.getLocalAddr()+":8000";
//        RECEIVE_TIME 收到请求的的时间
        String receiveTime = df.format(startTimeMillis);
//        ELAPSE_TIME, 耗时，等于 TIMESTAMP - RECEIVE_TIME
        String elapseTime = String.valueOf(endTimeMillis - startTimeMillis);
//        DUMMY 类名.方法名称
        String dummy = pjp.getTarget().getClass().getCanonicalName().substring(pjp.getTarget().getClass().getCanonicalName().lastIndexOf(".") + 1) + "." + pjp.getSignature().getName();
//        type 请求方式
        String type = request.getMethod();
//        REQUEST_BODY 请求参数，参数之间分割符为 ^_^
        String requestBody = LogUtils.getRequestBody(inputParamMap, request);
//        RESPONSE_BODY 响应参数，参数之间分割符为 ^_^
        String responseBody = LogUtils.getResponseBody(result);
//        RESPONSE_CODE 错误码
        int responseCode = LogUtils.getResultCode(result);

        String requestAuditLog = String.format(LOG_REQUEST_STR, proxyIp, clientIp, socId, requestId, serviceId, serverIp, receiveTime, elapseTime, type, dummy,requestBody, responseBody, responseCode);
        LogUtils.printLog(requestLogger, requestAuditLog);
        return result;
    }

}
