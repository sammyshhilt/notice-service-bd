package notice.service.bd.repository

import notice.service.bd.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByNickname(nickname: String): UserEntity?

}

