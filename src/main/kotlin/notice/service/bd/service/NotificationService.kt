package notice.service.bd.service

import notice.service.bd.model.NotificationDto

interface NotificationService {
    fun createNotification(notificationDto: NotificationDto): NotificationDto
    fun getNotificationsByUserId(userId: Long): List<NotificationDto>
    fun searchNotifications(text: String): List<NotificationDto>
    fun findNotificationById(id: Long): NotificationDto?
    fun findByUserIdAndDay(userId: Long, day: Int): List<NotificationDto>

}