package ar.edu.unq.desapp.grupoD.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

@Aspect
public class LogServiceExceptionAspect {

	private Logger logger;
	
	@AfterThrowing(pointcut="ar.edu.unq.desapp.grupoD.services.OperationService.saveOperation()",
	        throwing="ex")
//	@Around("execution(* saveOperation(..))")
//	public void handleException(final ProceedingJoinPoint pjp) throws Throwable{
	public void handleException(InvalidAmountException ex) {
		System.out.println("TIRO UNA EXCEPCION");
//		if (jp.getThis() != null) {
//			logger = Logger.getLogger(jp.getThis().getClass());
//		} else {
//			logger = Logger.getLogger(ExceptionHandler.class);
//		}
//		logger.error(jp.getThis().getClass() + " threw an exception.  The details are: " + e.getMessage());
	}
	
//	
//	@Around("execution(* comprar(..))")
//    public Object aroundGreeting(final ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("Buenas tardes, encantado de concerlo ... ");
//        try {
//            return pjp.proceed();
//        } finally {
//            System.out.println("Gracias, que tenga un buen dia .. hasta luego ");
//        }

	
}
