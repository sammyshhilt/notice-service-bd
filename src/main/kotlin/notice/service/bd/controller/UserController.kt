package notice.service.bd.controller

import jakarta.validation.Valid
import notice.service.bd.model.UserDto
import notice.service.bd.model.UserEntity
import notice.service.bd.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/notices/users")
class UserController(private val userRepository: UserRepository) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody @Valid userDto: UserDto) {

        if (userDto.id == null) {
            throw IllegalArgumentException("User ID cannot be null")
        }

        val  newUser = UserEntity(id = userDto.id, nickname = userDto.nickname)
        userRepository.save( newUser)
    }

    @DeleteMapping("/{nickname}")
    fun deleteUserByChatId(@PathVariable nickname: String): ResponseEntity<String> {
        val user = userRepository.findByNickname(nickname)
        return if (user != null) {
            userRepository.delete(user)
            ResponseEntity.ok("User and associated notifications deleted.")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/all")
    fun getAllUsersExcludingRequester(@RequestParam requesterId: Long): List<UserDto> {
        return userRepository.findAll()
            .filter { it.id != requesterId }
            .map { UserDto(id = it.id, nickname = it.nickname) }
    }

}
