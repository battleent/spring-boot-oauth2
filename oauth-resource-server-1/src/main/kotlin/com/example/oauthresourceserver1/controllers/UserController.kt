package com.example.oauthresourceserver1.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {

    @GetMapping("/me")
    fun me(): String {
        return "Client 1"
    }
}