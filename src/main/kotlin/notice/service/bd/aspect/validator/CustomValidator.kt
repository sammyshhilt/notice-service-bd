package notice.service.bd.aspect.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import notice.service.bd.aspect.AspectLogging
import notice.service.bd.aspect.annotation.CustomFieldAnnotation

class CustomValidator: ConstraintValidator<CustomFieldAnnotation, Int>{
    override fun isValid(value: Int, context: ConstraintValidatorContext?): Boolean {
        return value <= 31
    }
}