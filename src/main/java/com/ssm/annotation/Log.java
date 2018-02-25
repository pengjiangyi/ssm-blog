package com.ssm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @class Log
 * @description 自定义注解，AOP记录日志,拥有该注解的记录
 * @author 彭江毅   
 * @date 2018年2月10日下午6:59:40
 * @contact   741506070@qq.com
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Log {

	// 操作类型
	String description() default "";

}