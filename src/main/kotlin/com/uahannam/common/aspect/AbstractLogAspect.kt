package com.uahannam.common.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.reflect.MethodSignature
import java.lang.reflect.Method

abstract class AbstractLogAspect {

    protected fun extractMethodName(joinPoint: JoinPoint) : Method =
        (joinPoint.signature as MethodSignature).method
}