package notice.service.bd.model

import jakarta.persistence.*
import notice.service.bd.aspect.AspectLogging
import org.springframework.validation.annotation.Validated


@Entity
@Table(name="notification")
data class NotificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val text: String,

    @AspectLogging.CustomFieldAnnotation(message="Day need to be less than 31!")
    val day: Int,

    @Column(name = "userId")
    val userId: Long,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    val user: UserEntity? = null
)
