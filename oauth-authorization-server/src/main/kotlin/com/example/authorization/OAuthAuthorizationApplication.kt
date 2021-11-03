package com.example.authorization

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OAuthAuthorizationApplication

fun main(args: Array<String>) {
    runApplication<OAuthAuthorizationApplication>(*args)
}
