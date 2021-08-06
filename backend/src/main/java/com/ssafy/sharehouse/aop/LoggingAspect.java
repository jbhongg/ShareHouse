package com.ssafy.sharehouse.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before(value = "execution(* com.ssafy.happyhouse.model..Member*.*(..))")
	//@Before(value = "execution(* com.ssafy.happyhouse.model..*.*(..))")
	public void loggin(JoinPoint joinPoint) {
		//System.out.printf("메서드 선언부 : {%s} 전달 파라미터 : {%s}\n", joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()));
		logger.debug("메서드 선언부 : {} 전달 파라미터 : {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
	}

//	@Around(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))")
//	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//		StopWatch stopWatch = new StopWatch();
//		
//		stopWatch.start();
//		Object proceed = joinPoint.proceed();
//		stopWatch.stop();
//		
//		logger.debug("summary : {}", stopWatch.shortSummary());
//		logger.debug("totalTime : {}", stopWatch.getTotalTimeMillis());
//		logger.debug("pretty : \n{}", stopWatch.prettyPrint());
//		
//		return proceed;
//	}
//
//	@AfterReturning(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))", returning = "obj")
//	public void afterReturningMethod(JoinPoint joinPoint, Object obj) {
//		logger.debug("afterReturning call method : {} ", joinPoint.getSignature());
//		logger.debug("return value : {}", obj);
//	}
//
//	@AfterThrowing(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))", throwing = "exception")
//	public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) {
//		logger.debug("afterThrowing call method : {}", joinPoint.getSignature());
//		logger.debug("exception : {}", exception);
//	}
//
//	@After(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))")
//	public void afterMethod(JoinPoint joinPoint) {
//		logger.debug("after call method : {}", joinPoint.getSignature());
//	}

}
