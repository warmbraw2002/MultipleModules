package com.tiktok.quartz;

import com.tiktok.utils.SpringContextUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 通过反射的方式 调用真定需要执行的方法
 */
@Slf4j
@EqualsAndHashCode
@AllArgsConstructor
public class SchedulingRunnable implements Runnable {
    private String beanName;
    private String methodName;
    private String params;

    @Override
    public void run() {
        log.info("定时任务开始执行 - bean：{}，方法：{}，参数：{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();
        try {
            Object target = SpringContextUtils.getBean(beanName);
            Method method = null;
            if (StringUtils.hasText(params)) {
                method = target.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                method = target.getClass().getDeclaredMethod(methodName);
            }

            ReflectionUtils.makeAccessible(method);
            if (StringUtils.hasText(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception ex) {
            log.error("定时任务执行异常 - bean：{}，方法：{}，参数：{} ", beanName, methodName, params, ex);
        }

        long times = System.currentTimeMillis() - startTime;
        log.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", beanName, methodName, params, times);
    }
}