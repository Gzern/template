package com.example.template.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.example.template.controller.*.*(..))")
    public void webLog() {
    }

    //定义切点增强
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //日志记录内容
        String url = request.getRequestURL().toString();
        logger.info("url :" + url);
        logger.info("IP :" + request.getRemoteAddr());
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName();
        logger.info("CLASS_METHOD :" + classMethod);
        logger.info("ARGS :" + Arrays.toString(joinPoint.getArgs()));
        THREAD_LOCAL.set("url=" + url + ": class_method=" + classMethod);
        logger.info(THREAD_LOCAL.get() + "------ start visit");
    }


    //定义切点增强
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Exception {
        try {
            logger.info("RESPONSE :" + ret);
            logger.info(THREAD_LOCAL.get() + "------ finish visit");
        } finally {
            if (THREAD_LOCAL.get() != null) {
                THREAD_LOCAL.set(null);
            }
            THREAD_LOCAL.remove();
        }
    }
}
