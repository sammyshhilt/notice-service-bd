package notice.service.bd.aspect.annotation

import jakarta.validation.Constraint
import notice.service.bd.aspect.validator.CustomValidator
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CustomValidator::class])
annotation class CustomFieldAnnotation(
    val message: String = "Invalid value",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)