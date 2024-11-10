package notice.service.bd.controller

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import notice.service.bd.aspect.AspectLogging
import notice.service.bd.aspect.annotation.LoggerCustomClass
import notice.service.bd.model.NotificationDto
import notice.service.bd.service.NotificationService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("/notices")
class NotificationController(
    private val notificationService: NotificationService
) {



    @PostMapping
    fun createNotification(@RequestBody notificationDto: NotificationDto): NotificationDto {
        return notificationService.createNotification(notificationDto)
    }

    @GetMapping("/user/{userId}")
    fun getNotificationsByUserId(@PathVariable userId: Long): List<NotificationDto> {
        return notificationService.getNotificationsByUserId(userId)
    }

    @GetMapping("/search")
    fun searchNotifications(@RequestParam @NotBlank(message = "Notification need to be not blank!")text: String): List<NotificationDto> {
        return notificationService.searchNotifications(text)
    }

    @GetMapping("/{id}")
    fun findNotificationById(@PathVariable id: Long): NotificationDto? {
        return notificationService.findNotificationById(id)
    }

    @LoggerCustomClass("Finding notice by userId and day...")
    @GetMapping("/user/{userId}/day/{day}")
    fun getNotificationsByUserIdAndDay(@PathVariable userId: Long, @PathVariable @Min(1, message = "Day is always more or equal to 1!")day: Int): List<NotificationDto> {
        return notificationService.findByUserIdAndDay(userId, day)
    }

}