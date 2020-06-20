package com.yun.springboot.aop;

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
public class LogAspect {

    private static final String LOG_REQUEST_IN = "%s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s,  %s";

    private static final Logger requestLogger = LoggerFactory.getLogger("request_audit_logger");

    //时间格式
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    //拦截以下请求类型的消息体
    private static String[] contentTypes = {"application/x-www-form-urlencoded", "application/json", "text/xml", "text/html"};

    @Around("execution(* com..*.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        /**
         * 获取request信息
         */
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String contentType = request.getContentType();

        //是否拦截并包装请求，如果需要拦截则会获取RequestBody
        boolean filterRequestFlag = RequestUtils.checkContentType(contentType, contentTypes);
        // 其他类型的请求不拦截消息体
        Map<String, String[]> inputParamMap = null;
        if (filterRequestFlag) {
            inputParamMap = request.getParameterMap();
        }

        // 拦截的实体类，就是当前正在执行的controller
//        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
//        String methodName = pjp.getSignature().getName();
        // 拦截的放参数类型
        //Signature sig = pjp.getSignature();
        // 拦截的方法参数
        //Object[] args = pjp.getArgs();

        //String path = request.getContextPath();
        //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

        long startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
        Object result = pjp.proceed();//当使用环绕通知时，这个方法必须调用，否则拦截到的方法就不会再执行了
        long endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
//        TIMESTAMP  输出日志的时间戳
        String timestamp = "";
//        PROXY_IP 代理IP
        String proxyIp = RequestUtils.getProxyIP(request);
//        PROXY_PORT 代理端口
        String proxyPort = String.valueOf(request.getRemotePort());
//        CLIENT_IP 客户端IP
        String clientIp = RequestUtils.getRealIP(request);
//        CLIENT_PORT 客户端端口
        String clientPort = String.valueOf(request.getRemotePort());
//        APPID  扩展包头的APPID
        String appId = "0";
//        AREAID  扩展包头的AREAID
        String areaId = "0";
//        SOCID  客户端标识url
        String socId = "";
        if (RequestUtils.isEmpty(request.getRequestURI())) {
            socId = request.getQueryString();
        } else {
            socId = request.getRequestURI();
        }
//        REQUEST_ID 请求ID
        String requestId =(String) request.getAttribute("request_id");
//        XHEAD_UNIQUE_ID 扩展包头里的uniqueId, 若没有值则记录1
        String xheadUniqueId = "1";
//        SERVICEID  服务号
        String serviceId = "999999001";
//        MSGID  消息号
        String msgId = "0";
//        SERVER_IP 服务端IP
        String serverIp = request.getLocalAddr();
//        RECEIVE_TIME 收到请求的的时间
        String receiveTime = df.format(startTimeMillis);
//        ELAPSE_TIME, 耗时，等于 TIMESTAMP - RECEIVE_TIME
        String elapseTime = String.valueOf(endTimeMillis - startTimeMillis);
//        DUMMY 类名.方法名称
        String dummy = pjp.getTarget().getClass().getCanonicalName().substring(pjp.getTarget().getClass().getCanonicalName().lastIndexOf(".") + 1) + "." + pjp.getSignature().getName();
//        type 请求方式
        String type = request.getMethod();
//        IDX1  索引字段1
        String idx1 = "";
//        IDX2  索引字段2
        String idx2 = "";
//        IDX3  索引字段3
        String idx3 = "";
//        REQUEST_BODY 请求参数，参数之间分割符为 ^_^
        String requestBody = RequestUtils.getRequestBody(inputParamMap, request);
//        RESPONSE_BODY 响应参数，参数之间分割符为 ^_^
        String responseBody = RequestUtils.getResponseBody(result);
//        RESPONSE_CODE 错误码
        String responseCode = "";
        if (!RequestUtils.isEmpty(RequestUtils.getResultCode(result))) {
            responseCode = RequestUtils.getResultCode(result);
        } else {
            responseCode = "200";
        }

        timestamp = df.format(System.currentTimeMillis());
        String requestAuditLog = String.format(LOG_REQUEST_IN, timestamp, proxyIp, proxyPort, clientIp, clientPort, appId, areaId, socId, requestId, xheadUniqueId, serviceId, msgId, serverIp, receiveTime, elapseTime, type, dummy, idx1, idx2, idx3, requestBody, responseBody, responseCode);
        this.printOptLog(requestLogger, requestAuditLog);
        return result;
    }

    /**
     * @Title：printOptLog
     * @Description: 输出日志
     * @author guo
     */
    private void printOptLog(Logger logger, String logString) {
        logger.info(logString);
    }
}
