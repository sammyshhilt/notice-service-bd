package notice.service.bd.aspect.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import notice.service.bd.aspect.AspectLogging

class CustomValidator: ConstraintValidator<AspectLogging.CustomFieldAnnotation, Int>{
    override fun isValid(value: Int, context: ConstraintValidatorContext?): Boolean {
        return value <= 31
    }
}