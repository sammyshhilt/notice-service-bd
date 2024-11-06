package notice.service.bd.model

import notice.service.bd.aspect.AspectLogging

data class NotificationDto(
    val id: Long? = null,
    val text: String,
    @AspectLogging.CustomFieldAnnotation(message="Day need to be less than 31!")
    val day: Int,
    val userId: Long,
    val userNickname: String?
)
