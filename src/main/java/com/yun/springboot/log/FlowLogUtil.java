package com.yun.springboot.log;

import com.yun.springboot.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class FlowLogUtil {

    private static final String LOG_FLOW_STR = "%s,  %s,  %s,  %s,  %s,  %s,  %s";
    private static final Logger flow_audit_logger = LoggerFactory.getLogger("flow_audit_logger");
    //时间格式
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {
        FlowLogUtil.test();
    }

    private static void test() {
        FlowLogUtil.printLog("12312",System.currentTimeMillis(),System.currentTimeMillis(),new String("erqw"), 1,new String("return"));
    }

    public static void printLog(String requestId,Long startTimeMillis,Long endTimeMillis,Object... args) {
        //requestId
        String newReqId =requestId;
        //项目名称
        String serviceId = "yun-java-poetry";
//        RECEIVE_TIME 收到请求的的时间
        String receiveTime = df.format(startTimeMillis);
//        ELAPSE_TIME, 耗时，等于 TIMESTAMP - RECEIVE_TIME
        String elapseTime = String.valueOf(endTimeMillis - startTimeMillis);

        Thread thread = Thread.currentThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[2];
        String className = stackTraceElement.getClassName();
        String classNameMin = className.substring(className.lastIndexOf(".") + 1, className.length());
        String methodName = stackTraceElement.getMethodName();
        //DUMMY 类名.方法名称
        String dummy =classNameMin + "." + methodName;
        String reqStrJson = LogUtils.getRequestJson(args);
        String respStrJson=LogUtils.getRespJson(args);

        String flowLogStr = String.format(LOG_FLOW_STR,newReqId,serviceId,receiveTime,elapseTime,dummy,reqStrJson,respStrJson);
        LogUtils.printLog(flow_audit_logger, flowLogStr);
    }

}
