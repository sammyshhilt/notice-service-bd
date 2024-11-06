package notice.service.bd.aspect

import jakarta.validation.Constraint
//import jakarta.validation.Payload
import notice.service.bd.aspect.validator.CustomValidator
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import org.springframework.web.bind.MethodArgumentNotValidException
import kotlin.reflect.KClass



@Aspect
class AspectLogging {

    @Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @Retention(AnnotationRetention.RUNTIME)
    @Constraint(validatedBy = [CustomValidator::class])
    annotation class CustomFieldAnnotation(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<*>> = []
    )


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

    // обрабатывается фреймворком Spring до того, как оно дойдёт до аспекта AOP

//    @AfterThrowing(pointcut = "execution(* notice.service.bd.controller.UserController.createUser(..))", throwing = "exception")
//    fun handleValidCaseExceptions(ex: MethodArgumentNotValidException, joinPoint: JoinPoint, ){
//        val errors = mutableMapOf<String, String>()
//        ex.bindingResult.fieldErrors.forEach { error ->
//            errors[error.field] = error.defaultMessage ?: "Validation error"
//        }
//
//        logger.warn("${ex.message} -\t\t$errors")
//    }

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
