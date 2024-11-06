package notice.service.bd.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory

@Aspect
@Component
class AspectLogging {

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoggerCustomClass(val value: String = "")

    private val logger = LoggerFactory.getLogger(AspectLogging::class.java)

    @Before("@annotation(logExecution)")
    fun logCustomClassBefore(logExecution: LoggerCustomClass){
        logger.info("Before execution method of custom class SpringAOP: ${logExecution.value}")
    }


    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    fun logBeforeAllMethods(joinPoint: JoinPoint) {
        logger.info("Before executing method: ${joinPoint.signature.name}")
    }

    @After("within(@org.springframework.web.bind.annotation.RestController *)")
    fun logAfterAllMethods(joinPoint: JoinPoint) {
        logger.info("After executing method: ${joinPoint.signature.name}")
    }

    @Around("execution(* notice.service.bd.controller.NotificationController.createNotification(..)) || " +
            "execution(* notice.service.bd.controller.NotificationController.getNotificationsByUserId(..))")
    fun logAroundSpecificMethods(joinPoint: ProceedingJoinPoint): Any? {
        val methodName = joinPoint.signature.name
        val args = joinPoint.args.joinToString()
        logger.info("Around - Before executing $methodName with args: $args")

        val result = joinPoint.proceed()

        logger.info("Around - After executing $methodName, result: $result")
        return result
    }

    @AfterReturning(pointcut = "execution(* notice.service.bd.controller.NotificationController.findNotificationById(..))", returning = "result")
    fun logAfterReturning(joinPoint: JoinPoint, result: Any?) {
        logger.info("AfterReturning - Method ${joinPoint.signature.name} returned: $result")
    }

    @AfterThrowing(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", throwing = "exception")
    fun logAfterThrowing(joinPoint: JoinPoint, exception: Throwable) {
        logger.error("Exception in method ${joinPoint.signature.name}: ${exception.message}")
    }
}
