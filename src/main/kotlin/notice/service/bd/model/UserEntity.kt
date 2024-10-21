package notice.service.bd.model

import jakarta.persistence.*


@Entity
@Table(name="tguser")
data class UserEntity(
    @Id
    @Column(nullable = false, unique = true)
    val id: Long,  // Telegram user ID (не может быть null)
    val nickname: String?
)
