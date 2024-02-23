package com.uahannam.common.aspect

import com.uahannam.common.util.CurrentTimeGenerator
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.Exception

@Aspect
@Component
class WebLogAspect : AbstractLogAspect() {

    private val logger = LoggerFactory.getLogger(WebLogAspect::class.java)

    @Pointcut("execution(* com.uahannam.order.adapter.in.web..*.*(..))")
    fun orderServiceBeforeExecute() {  /* 로깅을 위한 Target을 정하는 함수이므로 Body 불필요 */ }

    @Before("orderServiceBeforeExecute()")
    fun loggingBeforeRequest(joinPoint: JoinPoint) {
        val method = extractMethodName(joinPoint)

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

    @AfterThrowing(pointcut = "orderServiceBeforeExecute()", throwing = "exception")
    fun loggingAfterThrowingException(joinPoint: JoinPoint, exception: Exception) {
        val method = extractMethodName(joinPoint)

        logger.error("${method.name}() 처리 중 예외 발생!")
        logger.error("예외 발생 시간 = {${CurrentTimeGenerator.generateCurrentTime()}}")
    }
}