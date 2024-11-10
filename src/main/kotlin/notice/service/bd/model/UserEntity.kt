package notice.service.bd.model

import jakarta.persistence.*
import jakarta.validation.constraints.*
//import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "tguser")
data class UserEntity(
    @Id
    @Column(nullable = false, unique = true)
    val id: Long,

    val nickname: String?,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val notifications: List<NotificationEntity> = emptyList()
)