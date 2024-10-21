package notice.service.bd.converter

import notice.service.bd.model.NotificationDto
import notice.service.bd.model.NotificationEntity

object NotificationConverter {
    fun toDto(entity: NotificationEntity): NotificationDto {
        return NotificationDto(
            id = entity.id,
            text = entity.text,
            day = entity.day,
            userId = entity.userId ,
            userNickname = entity.user?.nickname
        )
    }

    fun toEntity(dto: NotificationDto): NotificationEntity {
        return NotificationEntity(
            id = dto.id ?: 0,
            text = dto.text,
            day = dto.day,
            userId = dto.userId,
            user = null

        )
    }
}
