package ar.edu.unq.desapp.grupoD.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogServiceExceptionAspect {
	
	private Logger logger;
	
	@AfterThrowing(pointcut = "execution(  * ar.edu.unq.desapp.grupoD.services..*(..)   )",throwing = "exception")
	public void logServiceException(JoinPoint jp,Exception exception) throws Throwable { 
		logger = Logger.getLogger(jp.getTarget().getClass());
		logger.error(exception);
	}
	
}