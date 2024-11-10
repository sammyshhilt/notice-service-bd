package notice.service.bd.aspect.annotation


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoggerCustomClass(val value: String)