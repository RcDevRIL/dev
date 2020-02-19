package fr.cesi.mostuselesswebsitesapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Aspect for logging execution of service and repository Spring components.
 *
 */
@Aspect
@Service
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Pointcut that matches all Spring beans in the application's controller packages.
	 */
	@Pointcut("execution(* fr.cesi.mostuselesswebsitesapi.controller.*.*(..))")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	@Pointcut("within(fr.cesi.mostuselesswebsitesapi.controller.*)")
	public void executionAnyPublicMethod() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void restController() {
	}

	@Before("applicationPackagePointcut() || executionAnyPublicMethod() || restController()")
	public void before(JoinPoint joinPoint) {
		// Advice
		log.info(" Log before method execution ");
		log.info(" Execution of method {}", joinPoint.getSignature());
	}

}
