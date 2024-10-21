package notice.service.bd.model

data class NotificationDto(
    val id: Long? = null,
    val text: String,
    val day: Int,
    val userId: Long,
    val userNickname: String?
)
