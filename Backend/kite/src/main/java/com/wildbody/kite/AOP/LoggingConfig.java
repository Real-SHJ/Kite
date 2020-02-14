package com.wildbody.kite.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingConfig {
	Logger logger = LoggerFactory.getLogger(LoggingConfig.class);

	// 모든 서비스에 대해 aop를 실행
	// Logging AOP
	@Around("execution(* com.wildbody.kite..*())")
	public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("start - " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " / "
				+ proceedingJoinPoint.getSignature().getName());
		Object result = proceedingJoinPoint.proceed();
		logger.info("finished - " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " / "
				+ proceedingJoinPoint.getSignature().getName());
		return result;
	}
}