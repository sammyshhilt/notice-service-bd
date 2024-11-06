package notice.service.bd.model

import jakarta.persistence.*
import jakarta.validation.constraints.*
//import jakarta.validation.constraints.NotNull


@Entity
@Table(name="tguser")
data class UserEntity(
    @Id
    @Column(nullable = false, unique = true)
    val id: Long,

//    @field: NotBlank(message = "nickname пользователя не может быть пустым!")
//    @field: Size(min=1, max=20, message = "tguser nickname need to be in range 1..20")
    val nickname: String?
)
