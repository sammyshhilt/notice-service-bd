package notice.service.bd.service.impl

import notice.service.bd.converter.NotificationConverter
import notice.service.bd.model.NotificationDto
import notice.service.bd.repository.NotificationRepository
import notice.service.bd.repository.UserRepository
import notice.service.bd.service.NotificationService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val notificationRepository: NotificationRepository,
    private val userRepository: UserRepository
) : NotificationService {

    override fun createNotification(notificationDto: NotificationDto): NotificationDto {
        val entity = NotificationConverter.toEntity(notificationDto)
        return NotificationConverter.toDto(notificationRepository.save(entity))
    }

    override fun getNotificationsByUserId(userId: Long): List<NotificationDto> {
        return notificationRepository.findAllByUserId(userId).map(NotificationConverter::toDto)
    }

    override fun searchNotifications(text: String): List<NotificationDto> {
        return notificationRepository.findByTextContaining(text).map(NotificationConverter::toDto)
    }


    override fun findNotificationById(id: Long): NotificationDto {
            val notificationEntity = notificationRepository.findByIdOrNull(id)
                ?: throw NoSuchElementException("Notification with ID $id not found")
            return NotificationConverter.toDto(notificationEntity)
    }

    override fun findByUserIdAndDay(userId: Long, day: Int): List<NotificationDto> {
        val notifications = notificationRepository.findByUserIdAndDay(userId, day)
        return notifications.map { NotificationConverter.toDto(it) }
    }

}