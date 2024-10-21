package notice.service.bd.controller

import notice.service.bd.model.UserDto
import notice.service.bd.model.UserEntity
import notice.service.bd.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notices/users")
class UserController(private val userRepository: UserRepository) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userDto: UserDto) {
        // Проверяем, что ID пользователя не null
        if (userDto.id == null) {
            throw IllegalArgumentException("User ID cannot be null")
        }

        // Сохраняем пользователя в базу данных
        val newUser = UserEntity(id = userDto.id, nickname = userDto.nickname)
        userRepository.save(newUser)
    }
}
