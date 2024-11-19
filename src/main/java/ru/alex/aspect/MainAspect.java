package ru.alex.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import ru.alex.Main;
import ru.alex.models.Task;

@Aspect
@Component
public class MainAspect {
    private static final Logger logger = LoggerFactory.getLogger(MainAspect.class.getName());

    @Before("@annotation(LogExecution)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("\u001b[34mMethod: " + joinPoint.getSignature().getName() + " was called\u001B[0m");
    }

    @AfterReturning(value = "@annotation(GetterExecution)", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        logger.info("\u001b[34mMethod: " + joinPoint.getSignature().getName() + " returned: " + result + "\u001B[0m");
    }

    @AfterThrowing("@annotation(ErrorExecution)")
    public void logError(JoinPoint joinPoint) {
        logger.error("\u001B[31mIncorrect userId: " + joinPoint.getSignature().getName() + ". Shutting down...\u001B[0m");

        SpringApplication.exit(Main.context);
    }

    @Around("@annotation(CheckIdExecution)")
    public Task checkId(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("\u001b[34mMethod: \" + joinPoint.getSignature().getName() + \" was called\\u001B[0m");

        Long userId = ((Task) joinPoint.getArgs()[1]).getUserId();

        if (userId > 0) {
            Task result = (Task)joinPoint.proceed();
            logger.info("\u001b[34mMethod: " + joinPoint.getSignature().getName() + " successfully returned: " +
                    result + "\u001B[0m");

            return result;
        }

        logger.warn("\u001B[33mMethod: " + joinPoint.getSignature().getName() + " returned no result," +
                "because of incorrect userId was given\u001B[0m");

        return null;
    }
}