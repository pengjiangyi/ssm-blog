package com.ssm.annotation;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssm.pojo.Syslog;
import com.ssm.pojo.User;
import com.ssm.service.dao.LoginService;
import com.ssm.service.dao.SyslogService;
import com.ssm.util.IpUtil;
/**
 * 
	*@class SystemLogAspect
	*@description  
 	*@author 彭江毅   
 	*@date  2018年2月10日下午7:00:15
 	*@contact  741506070@qq.com
 */
@Aspect
@Component
public class SystemLogAspect {

	@Resource
	private SyslogService service;

	/**
	 * Controller层切点 注解拦截
	 */
	@Pointcut("@annotation(Log)")
	public void controllerAspect() {
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return discription
	 */
	public static String getControllerMethodDescription2(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Log log = method.getAnnotation(Log.class);
		String discription = log.description();
		return discription;
	}

	@Around("controllerAspect()")
	public Object doAroundMethod(ProceedingJoinPoint pjd) throws Throwable {
		long startTime = System.currentTimeMillis();
		//获取参数
		Object[] params = pjd.getArgs();
		String par = "";
		for (Object param : params) {
			par += "[" + param + "]";
		}
		// 下面为方法执行后,获取用户session
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getRequest();
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				//获取ip
				String ip = IpUtil.getIpAddr(request);
				String username = "";
				Integer userid = -1;
				if (user != null) {
					username = user.getUsername();
					userid = user.getId();
				} else {
					username = "游客";
				}
		
		
		String method = pjd.getSignature().getName();
		// ###################上面代码为方法执行前#####################
		Object result = pjd.proceed();// 执行方法，获取返回参数
		// ###################下面代码为方法执行后#####################

		
		String operType = getControllerMethodDescription2(pjd);// 操作类型
		String requestUri = request.getRequestURI();// 请求的Uri
		String methodType = request.getMethod(); // 请求的方法类型(post/get
		long endtime = System.currentTimeMillis();
		String timeout = String.valueOf((endtime - startTime));
		
		Syslog log = new Syslog();
		log.setIp(ip);
		log.setMethodtype(methodType);
		log.setMethod(method);
		log.setParams(par);
		log.setUsername(username);
		log.setUserid(userid);
		log.setUrl(requestUri);
		log.setTitle(operType);
		log.setResult(result.toString());
		log.setStarttime(new Date());
		log.setTimeout(timeout);
		log.setType("INFO");
		service.addLog(log);
		
		return result;
	}

	/**
	 * 异常通知 记录操作报错日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		String operType = getControllerMethodDescription2(joinPoint);// 操作类型
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String ip = IpUtil.getIpAddr(request);
		String username = "";
		Integer userid = -1;
		if (user != null) {
			username = user.getUsername();
			userid = user.getId();
		} else {
			username = "游客";
		}
		Object[] params = joinPoint.getArgs();
		String par = "";
		for (Object param : params) {
			par += "[" + param + "]";
		}
		String method = joinPoint.getSignature().getName();
		String requestUri = request.getRequestURI();// 请求的Uri
		String methodType = request.getMethod(); // 请求的方法类型(post/get
		
		Syslog log = new Syslog();
		log.setException(e.toString());
		log.setParams(par);
		log.setUsername(username);
		log.setUserid(userid);
		log.setType("ERROR");
		log.setStarttime(new Date());
		log.setMethod(method);
		log.setMethodtype(methodType);
		log.setTitle(operType);
		log.setUrl(requestUri);
		log.setIp(ip);
		service.addLog(log);

	}
}
