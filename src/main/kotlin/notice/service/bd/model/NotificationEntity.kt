package notice.service.bd.model

import jakarta.persistence.*

@Entity
@Table(name="notification")
data class NotificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val text: String,

    val day: Int,

    @Column(name = "userId") // указываем, что это поле не будет изменяться через NotificationEntity
    val userId: Long,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false) // Оставляем связь с UserEntity // связываем с полем id в UserEntity
    val user: UserEntity? = null// Связь с таблицей пользователей, добавляем ? для возможности null
)
