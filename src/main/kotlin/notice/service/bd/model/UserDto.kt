package notice.service.bd.model

data class UserDto(
    val id: Long, // Может быть null при создании нового пользователя
    val nickname: String?
)
