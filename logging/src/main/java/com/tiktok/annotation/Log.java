package com.tiktok.annotation;
 
import java.lang.annotation.*;
 
/**
 * @description TODO 自定义记录日志的注解
 */
 
/**
 * 此注解表示注解扫描的范围，一般取值如下，这里我们范围设到注解即可
 *      1.CONSTRUCTOR:用于描述构造器
 *      2.FIELD:用于描述域
 *      3.LOCAL_VARIABLE:用于描述局部变量
 *      4.METHOD:用于描述方法
 *      5.PACKAGE:用于描述包
 *      6.PARAMETER:用于描述参数
 *      7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 * @author Administrator
 */
@Target(ElementType.METHOD)
/**
 * 此注解表示注解在哪个阶段执行，RUNTIME表示在运行时执行
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 *      如果只是做一些检查性的操作，比如 @Override 和 @SuppressWarnings，选择用RESOURCE注解
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 *      一般用在需要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife）
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 *      一般用在需要在运行时去动态获取注解信息的场景下
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
 
    /**
     * 操作模块
     */
    String operModule() default "";
 
    /**
     * 操作类型
     */
    String operType() default "";
 
    /**
     * 操作说明
     */
    String operDesc() default "";
}