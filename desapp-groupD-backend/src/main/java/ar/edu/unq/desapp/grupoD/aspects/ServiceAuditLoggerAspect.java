package ar.edu.unq.desapp.grupoD.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * This class logs the audit information of the services invocations
 * @author Lucas
 *
 */
@Aspect
public class ServiceAuditLoggerAspect {

	private Logger logger;
	
	@Before("execution(* ar.edu.unq.desapp.grupoD.services..*(..))  ")
	public void logBefore(JoinPoint joinPoint) {
		logger = Logger.getLogger(joinPoint.getTarget().getClass());
		String parameterList = buildParameterList( joinPoint);
		logger.info("Received invocation to method: '" + joinPoint.getSignature().getName() + "'. With parameters: " + parameterList);
	}

	private String buildParameterList(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		String[] parameterNames = signature.getParameterNames();
		Object[] parameterValues = joinPoint.getArgs();
		String result = "";
		
		for(int i = 0 ; i<parameterNames.length ; i++){
			result = result.concat( "{" + parameterNames[i] + " : " + parameterValues[i] + "}" );
		}
		
		return result;
	}
	
}
