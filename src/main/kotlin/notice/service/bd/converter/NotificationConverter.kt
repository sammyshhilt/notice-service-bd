package notice.service.bd.converter

import notice.service.bd.model.NotificationDto
import notice.service.bd.model.NotificationEntity
import notice.service.bd.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class NotificationConverter(private val userRepository: UserRepository) {



    fun toDto(entity: NotificationEntity): NotificationDto {
        return NotificationDto(
            id = entity.id,
            text = entity.text,
            day = entity.day,
            userId = entity.userId ,
            userNickname = entity.user.nickname
        )
    }

    fun toEntity(dto: NotificationDto): NotificationEntity {
        val user = userRepository.findById(dto.userId)
            .orElseThrow { IllegalArgumentException("User not found with ID: ${dto.userId}") }

        return NotificationEntity(
            id = dto.id ?: 0,
            text = dto.text,
            day = dto.day,
            userId = dto.userId,
            user = user

        )
    }
}
