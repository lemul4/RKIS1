package org.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Pointcut("execution(* org.example..*(..)) && !within(org.example.aspects.LoggerAspect)")
    public void applicationMethods() {}

    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("\n[LOG] Вызов метода: " + joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("[LOG] Параметры: ");
            for (Object arg : args) {
                System.out.println(" - " + arg);
            }
        }
    }

    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[LOG] Метод завершен: " + joinPoint.getSignature().toShortString());
        if (result != null) {
            System.out.println("[LOG] Возвращаемое значение: " + result);
        }
    }

    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("[LOG] Метод вызвал исключение: " + joinPoint.getSignature().toShortString());
        System.out.println("[LOG] Исключение: " + exception.getClass().getName() + " - " + exception.getMessage());
    }
}