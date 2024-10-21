package notice.service.bd.repository

import notice.service.bd.model.NotificationEntity
import org.springframework.data.repository.CrudRepository

interface NotificationRepository : CrudRepository<NotificationEntity, Long> {
    fun findAllByUserId(id: Long): List<NotificationEntity>
    fun findByTextContaining(text: String): List<NotificationEntity>
    fun findByUserIdAndDay(userId: Long, day: Int): List<NotificationEntity>

}
