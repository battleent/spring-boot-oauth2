package com.example.lckdive.controllers

import com.example.lckdive.responses.UserResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/users")
class UserController(
    val restTemplate: RestTemplate
) {

    @GetMapping("/me")
    fun me(@RequestHeader authorization: String): UserResponse? {
        val headers = HttpHeaders().apply {
            this["Authorization"] = authorization
        }
        val entity = HttpEntity(null, headers)
        return restTemplate.exchange("http://localhost:8081/users/me", HttpMethod.GET, entity, UserResponse::class.java).body
    }
}