package com.uahannam.common.aspect

import com.uahannam.common.util.CurrentTimeGenerator
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.Exception

@Aspect
@Component
class KafkaLogAspect : AbstractLogAspect() {

    private val logger = LoggerFactory.getLogger(KafkaLogAspect::class.java)

    @Pointcut("execution(* com.uahannam.order.adapter.out.messaging..*.*(..))")
    fun kafkaMessagingBeforeExecute() {  /* 로깅을 위한 Target을 정하는 함수이므로 Body 불필요 */ }

    @Before("kafkaMessagingBeforeExecute()")
    fun loggingBeforeMessaging(joinPoint: JoinPoint) {
        val method = extractMethodName(joinPoint)

        logger.info("${method.name}() Kafka 메시지 발송 시작")
        logger.info("${method.name}() Kafka 메시지 발송 요청 시간 => {}", CurrentTimeGenerator.generateCurrentTime())

        val paramArgs = joinPoint.args

        for (arg in paramArgs) {
            paramArgs?.let {
                logger.info("Parameter Type => {}", arg.javaClass.simpleName)
                logger.info("Parameter Value => {}", arg)
            }
        }
    }

    @AfterReturning("kafkaMessagingBeforeExecute()")
    fun loggingAfterMessaging(joinPoint: JoinPoint) {
        val method = (joinPoint.signature as MethodSignature).method

        logger.info("메시지 발송 종료 => {}", CurrentTimeGenerator.generateCurrentTime())
        logger.info("메시지 발송 종료, 종료된 함수 => {}", method.name)
    }

    @AfterThrowing(pointcut = "kafkaMessagingBeforeExecute()", throwing = "exception")
    fun loggingAfterThrowingException(joinPoint: JoinPoint, exception: Exception) {
        val method = extractMethodName(joinPoint)

        logger.error("${method.name}() 처리 중 예외 발생!")
        logger.error("예외 발생 시간 = {${CurrentTimeGenerator.generateCurrentTime()}}")
    }
}