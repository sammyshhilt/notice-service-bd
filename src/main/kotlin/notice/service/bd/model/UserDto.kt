package notice.service.bd.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UserDto(
    @field: NotNull(message = "ID пользователя не может быть пустым!")
    val id: Long,
    @field: NotBlank(message = "nickname пользователя не может быть пустым!")
    @field: Size(min=1, max=20, message = "tguser nickname need to be in range 1..20")
    val nickname: String?
)
