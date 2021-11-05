package com.example.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthResourceServerAccountApplication

fun main(args: Array<String>) {
    runApplication<OauthResourceServerAccountApplication>(*args)
}
