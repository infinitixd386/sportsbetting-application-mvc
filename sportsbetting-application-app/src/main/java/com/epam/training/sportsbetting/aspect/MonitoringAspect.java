package com.epam.training.sportsbetting.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class MonitoringAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);
    private static final String POINTCUT_EXPRESSION_STRING = "execution(* com.epam.training.sportsbetting.service.SportsBettingService.";

    public MonitoringAspect() {
    }

    @Around(POINTCUT_EXPRESSION_STRING+"authenticateUser(..))")
    public Object loggingAuthenticateUser(final ProceedingJoinPoint joinPoint) throws Throwable{
        loggingBefore(joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()).get(0).toString());
        long startMicroTime = System.nanoTime();
        Object result = joinPoint.proceed();
        loggingAfter(joinPoint.getSignature().getName(), result.toString(), startMicroTime);
        return result;
    }

    @Around(POINTCUT_EXPRESSION_STRING+"findAllBets(..))")
    public Object loggingFindAllBets(final ProceedingJoinPoint joinPoint) throws Throwable{
        loggingBefore(joinPoint.getSignature().getName());
        long startMicroTime = System.nanoTime();
        Object result = joinPoint.proceed();
        loggingAfter(joinPoint.getSignature().getName(), result.toString(), startMicroTime);
        return result;
    }

    @Around(POINTCUT_EXPRESSION_STRING+"createWager(..))")
    public Object loggingCreateWager(final ProceedingJoinPoint joinPoint) throws Throwable{
        loggingBefore(joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()).get(0).toString(),
                Arrays.asList(joinPoint.getArgs()).get(1).toString(), Arrays.asList(joinPoint.getArgs()).get(2).toString());
        long startMicroTime = System.nanoTime();
        Object result = joinPoint.proceed();
        loggingAfter(joinPoint.getSignature().getName(), result.toString(), startMicroTime);
        return result;
    }

    @Around(POINTCUT_EXPRESSION_STRING+"findAllWagers(..))")
    public Object loggingFindAllWagers(final ProceedingJoinPoint joinPoint) throws Throwable{
        loggingBefore(joinPoint.getSignature().getName());
        long startMicroTime = System.nanoTime();
        Object result = joinPoint.proceed();
        loggingAfter(joinPoint.getSignature().getName(), result.toString(), startMicroTime);
        return result;
    }

    @Around(POINTCUT_EXPRESSION_STRING+"calculateResults(..))")
    public void loggingCalculateResults(final ProceedingJoinPoint joinPoint) throws Throwable{
        loggingBefore(joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
        long startMicroTime = System.nanoTime();
        joinPoint.proceed();
        loggingAfter(joinPoint.getSignature().getName(), startMicroTime);
    }

    private void loggingBefore(String methodName, String parameter){
        LOGGER.debug(String.format("Method name: [%s], parameter(s): (%s)",methodName, parameter));
    }

    private void loggingBefore(String methodName){
        LOGGER.debug(String.format("Method name: [%s], parameter(s): ()",methodName));
    }

    private void loggingBefore(String methodName, String firstParameter, String secondParameter, String thirdParameter){
        LOGGER.debug(String.format("Method name: [%s], parameter(s): (%s, %s, %s)",methodName, firstParameter, secondParameter, thirdParameter));
    }

    private void loggingBefore(String methodName, Object parameter){
        LOGGER.debug(String.format("Method name: [%s], parameter(s): %s",methodName, parameter));
    }

    private long timeMeasurement(long startMicroTime){
        long endMicroTime = System.nanoTime();
        return (endMicroTime - startMicroTime) / 1000;
    }

    private void loggingAfter(String methodName, String returnValue, long startMicroTime){
        LOGGER.debug(String.format("Method name: [%s], return value: [%s]",methodName, returnValue));
        LOGGER.debug("Execution time = "+timeMeasurement(startMicroTime)+" μs");
    }

    private void loggingAfter(String methodName, long startMicroTime){
        LOGGER.debug(String.format("Method name: [%s], return value: []",methodName));
        LOGGER.debug("Execution time = "+timeMeasurement(startMicroTime)+" μs");
    }

}
