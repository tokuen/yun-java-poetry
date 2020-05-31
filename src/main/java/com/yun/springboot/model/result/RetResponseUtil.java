package com.yun.springboot.model.result;

public class RetResponseUtil {
 
   private final static String SUCCESS = "success";

   public static <T> RetResult<T> makeOKRsp() {
      return new RetResult<T>().setCode(ErrorCode.SUCCESS.code).setMsg(ErrorCode.SUCCESS.message);
   }

   public static <T> RetResult<T> makeOKRsp(T data) {
      return new RetResult<T>().setCode(ErrorCode.SUCCESS).setMsg(SUCCESS).setData(data);
   }

   public static <T> RetResult<T> makeRsp(ErrorCode errorCode) {
      return new RetResult<T>().setCode(errorCode.code).setMsg(errorCode.message);
   }

   public static <T> RetResult<T> makeRsp(ErrorCode errorCode, T data) {
      return new RetResult<T>().setCode(errorCode.code).setMsg(errorCode.message).setData(data);
   }
}