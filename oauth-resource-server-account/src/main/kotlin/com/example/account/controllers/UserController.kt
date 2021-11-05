package com.example.account.controllers

import com.example.account.entities.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/users")
class UserController {

    @GetMapping("/me")
    fun me() = User().apply {
        this.id = 1
        this.username = "user"
        this.createdAt = LocalDateTime.now()
    }
}