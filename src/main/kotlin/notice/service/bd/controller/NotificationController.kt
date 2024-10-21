package notice.service.bd.controller

import notice.service.bd.model.NotificationDto
import notice.service.bd.service.NotificationService
import org.springframework.web.bind.annotation.*

@RestController
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
    fun searchNotifications(
        @RequestParam text: String,
    ): List<NotificationDto> {
        return notificationService.searchNotifications(text)
    }

    @GetMapping("/{id}")
    fun findNotificationById(@PathVariable id: Long): NotificationDto? {
        return notificationService.findNotificationById(id)
    }

    @GetMapping("/user/{userId}/day/{day}")
    fun getNotificationsByUserIdAndDay(@PathVariable userId: Long, @PathVariable day: Int): List<NotificationDto> {
        return notificationService.findByUserIdAndDay(userId, day)
    }

}