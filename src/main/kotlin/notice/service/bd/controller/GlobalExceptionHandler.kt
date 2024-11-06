package notice.service.bd.controller

import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        ex.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage ?: "Validation error"
        }

        // Логгируем ошибки
        logger.info("Validation error: ${ex.message} - Errors: $errors")

        return errors
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        ex: ConstraintViolationException
    ): ResponseEntity<Map<String, String>> {
        val errors = mutableMapOf<String, String>()

        ex.constraintViolations.forEach { violation ->
            val field = violation.propertyPath.toString()
            val message = violation.message
            errors[field] = message
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}


