package notice.service.bd.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import notice.service.bd.aspect.AspectLogging
import notice.service.bd.aspect.annotation.CustomFieldAnnotation
import org.springframework.validation.annotation.Validated


@Entity
@Table(name = "notification")
data class NotificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val text: String,

    val day: Int,

    @Column(name = "userId", insertable = false, updatable = false, nullable = false)
    val userId: Long,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @NotNull
    val user: UserEntity
)
