package com.exbrotas.bag.config.aop;

import java.util.UUID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPLogging {

  private static final String ANSI_BOLD = "\u001B[1m";
  private static final String ANSI_BLUE = "\u001B[34m";
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";
  private static final Logger LOGGER = LoggerFactory.getLogger(AOPLogging.class);

  @Pointcut("execution(* com.exbrotas.bag.repositories..*(..)) || execution(* com.exbrotas.bag.mappers..*(..))")
  public void repositoryMonitor() {
  }

  @Pointcut("within(com.exbrotas.bag.controller..*)")
  public void controllerMonitor() {
  }

  @Around("repositoryMonitor()")
  public Object profileRepository(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    String className = getClassName(joinPoint);
    String methodName = joinPoint.getSignature().getName();

    MDC.put("repoTrace", "%s.%s".formatted(className, methodName));
    try {
      LOGGER.debug("{}{}Iniciando execucao de {}.{}{}",
          ANSI_BOLD, ANSI_PURPLE, className, methodName, ANSI_RESET);

      return joinPoint.proceed();
    } finally {
      long elapsed = System.currentTimeMillis() - start;
      LOGGER.info("Metodo {}{}{}.{}{} demorou {}{} ms{}",
          ANSI_BOLD, ANSI_PURPLE, className, methodName, ANSI_RESET, ANSI_BLUE, elapsed,
          ANSI_RESET);
      MDC.remove("repoTrace");
    }
  }

  @Around("controllerMonitor()")
  public Object profileController(ProceedingJoinPoint joinPoint) throws Throwable {
    String traceId = UUID.randomUUID().toString().substring(0, 13);

    MDC.put("traceId", traceId);
    try {
      return joinPoint.proceed();
    } finally {
      MDC.remove("traceId");
    }
  }

  private String getClassName(ProceedingJoinPoint joinPoint) {
    String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
    int packageIndex = declaringTypeName.lastIndexOf('.');

    if (packageIndex < 0) {
      return declaringTypeName;
    }
    return declaringTypeName.substring(packageIndex + 1);
  }
}
