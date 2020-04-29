package com.kh.mohagee.common.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mohagee.member.model.vo.Member;

@Component
@Aspect
public class AfterReturningLoginLogger {
	
	private Logger logger = LoggerFactory.getLogger(AfterReturningLoginLogger.class);
	
	@Pointcut("execution(* com.kh.mohagee..MemberController.*Login(..))")
	public void loginPointCut() {}
	
	@AfterReturning(pointcut="loginPointCut()", returning="returnObj")
	public void myAdvice(JoinPoint jp, Object returnObj) {
		
		ModelAndView mv = (ModelAndView)returnObj;
		
		Map<String,Object> map = mv.getModel();
		
		if(map.containsKey("member")) {
			Member m = (Member)map.get("member");
			
			logger.debug("[" + m.getUserId() + "] 님이 로그인하였습니다.");
		}
	}
}










