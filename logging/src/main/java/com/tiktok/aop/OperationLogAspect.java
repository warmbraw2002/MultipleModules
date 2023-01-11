package com.tiktok.aop;

import com.alibaba.fastjson.JSON;
import com.tiktok.annotation.Log;
import com.tiktok.domain.SysLog;
import com.tiktok.service.SysLogService;
import com.tiktok.utils.AgentUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
/**
 * @description TODO 切面的处理类，用来处理操作日志和异常日志
 */
@Component
/**
 * 加上此注解，Spring在扫描到此类的时候会将此类作为AOP容器进行处理
 */
@Aspect
public class OperationLogAspect {
 
    @Autowired
    private SysLogService sysLogService;
 
    /**
     * 设置操作日志的切入点，用来记录操作日志，在标明注解的位置切入
     */
    @Pointcut("@annotation(com.tiktok.annotation.Log)")
    public void operationLogPointCut() {
 
    }
 
    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.tiktok.controller..*.*(..))")
    public void operErrorLogPointCut() {
    }
 
    /**
     * 设置操作异常切入点，拦截用户的操作日志，连接点正常执行后执行，若连接点抛出异常则不会执行
     * @param joinPoint 切入点
     * @param keys 返回结果
     */
    @AfterReturning(value = "operationLogPointCut()", returning = "keys")
    public void saveOperationLog(JoinPoint joinPoint, Object keys) {
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取到的RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        SysLog sysLog = new SysLog();
        sysLog.setLogType("operation");
        //OperationLog operationLog = new OperationLog();
 
        try {
            //在切面织入点通过反射机制获取织入点的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取织入点的方法
            Method method = signature.getMethod();
            //获取操作
            Log log = method.getAnnotation(Log.class);
            if (log != null) {
                String operModule = log.operModule();
                String operDesc = log.operDesc();
                String operType = log.operType();
                sysLog.setDescription(operDesc);
//                operationLog.setOperModule(operModule);
//                operationLog.setOperDesc(operDesc);
//                operationLog.setOperType(operType);
            }
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法
            String methodName = method.getName();
            methodName = className + "." + methodName;
            //请求方法
            sysLog.setMethod(methodName);
//            operationLog.setOperMethod(methodName);
 
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            //将参数所在的数组转为json
            String params = JSON.toJSONString(rtnMap);
 
            //请求参数
            sysLog.setParams(params);
//            operationLog.setOperRequParam(params);
            //返回结果
//            operationLog.setOperRespParam(JSON.toJSONString(keys));
            //操作员ip地址
            sysLog.setRequestIp(AgentUtil.getIpAddr(request));
//            operationLog.setOperIp(IpUtils.getIpAddr(request));
            //操作员浏览器
            sysLog.setBrowser(AgentUtil.getBrowser(request));
            //请求IP物理地址
//            operationLog.setOperUri(request.getRequestURI());
            sysLog.setAddress(AgentUtil.getAddress(sysLog.getRequestIp()));
            //创建时间（操作时间）
            sysLog.setCreateTime(new Date());
            sysLog.setTime(new Date().getTime());
            sysLog.setUsername("admin");
//            operationLog.setOperCreateTime(new Date());
//            operationLog.setOperUserId("1");
//            operationLog.setOperUserName("测试");
            sysLogService.saveLog(sysLog);
            //operationLogService.save(operationLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
 
    /**
     * 异常返回通知，用于拦截异常日志的信息，连接点抛出异常后执行
     * @param joinPoint 切入点
     * @param e 异常信息
     */
    @AfterThrowing(pointcut = "operErrorLogPointCut()", throwing = "e")
    public void saveErrorLog(JoinPoint joinPoint,Throwable e){
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
 
        SysLog sysLog = new SysLog();
        sysLog.setLogType("error");
        //ErrorLog errorLog = new ErrorLog();
        try {
            //在切面织入点通过反射机制获取织入点的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取织入点的方法
            Method method = signature.getMethod();
            //获取操作
            Log log = method.getAnnotation(Log.class);
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法
            String methodName = method.getName();
            methodName = className + "." + methodName;
            //请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            String params = JSON.toJSONString(rtnMap);
            //请求参数
            sysLog.setParams(params);
            //errorLog.setErrRequParam(params);
            //请求方法名
            sysLog.setMethod(methodName);
            //errorLog.setOperMethod(methodName);
            //异常名称
            sysLog.setDescription(e.getClass().getName());
            //errorLog.setErrName(e.getClass().getName());
            //异常信息
            sysLog.setExceptionDetail(stackTraceToString(e.getClass().getName(),e.getMessage(),e.getStackTrace()));
            //errorLog.setErrMessage(stackTraceToString(e.getClass().getName(),e.getMessage(),e.getStackTrace()));
            //请求URI
            //errorLog.setOperUri(request.getRequestURI());
            //操作员ip地址
            sysLog.setRequestIp(AgentUtil.getIpAddr(request));
            //errorLog.setOperIp(IpUtils.getIpAddr(request));
            //发生异常的时间
            sysLog.setCreateTime(new Date());
            sysLog.setTime(new Date().getTime());
            sysLog.setUsername("admin");
//            errorLog.setOperCreateTime(new Date());
//            errorLog.setOperUserId("1");
//            errorLog.setOperUserName("测试");
 
            sysLogService.saveLog(sysLog);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
 
    /**
     * 转换request请求参数
     * @param paramMap request中获取的参数数组
     * @return 转换后的数组
     */
    public Map<String, String> converMap(Map<String,String[]> paramMap){
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key,paramMap.get(key)[0]);
        }
        return rtnMap;
    }
 
    public String stackTraceToString(String exceptionName,String exceptionMessage,StackTraceElement[] elements){
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement element : elements) {
            stringBuilder.append(element).append("\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + stringBuilder.toString();
        return message;
    }
 
}