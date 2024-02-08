package com.uahannam.common.aspect

import com.uahannam.common.util.CurrentTimeGenerator
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LogAspect {

    private val logger = LoggerFactory.getLogger(LogAspect::class.java)

    @Pointcut("execution(* com.uahannam.order.adapter.in.web..*.*(..))")
    fun orderServiceBeforeExecute() {  /* 로깅을 위한 Target을 정하는 함수이므로 Body 불필요 */ }

    @Before("orderServiceBeforeExecute()")
    fun loggingBeforeRequest(joinPoint: JoinPoint) {
        val method = (joinPoint.signature as MethodSignature).method

        logger.info("${method.name}() 요청 처리 시작")
        logger.info("${method.name}() 요청 시간 => {}", CurrentTimeGenerator.generateCurrentTime())

        val paramArgs = joinPoint.args

        for (arg in paramArgs) {
            paramArgs?.let {
                logger.info("Parameter Type => {}", arg.javaClass.simpleName)
                logger.info("Parameter Value => {}", arg)
            }
        }
    }

    @AfterReturning("orderServiceBeforeExecute()")
    fun loggingAfterRequest(joinPoint: JoinPoint) {
        val method = (joinPoint.signature as MethodSignature).method

        logger.info("요청 종료 시간 => {}", CurrentTimeGenerator.generateCurrentTime())
        logger.info("요청 종료, 종료된 함수 => {}", method.name)
    }
}