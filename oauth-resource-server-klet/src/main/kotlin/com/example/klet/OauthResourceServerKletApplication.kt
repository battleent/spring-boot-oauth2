package com.example.klet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthResourceServerKletApplication

fun main(args: Array<String>) {
    runApplication<OauthResourceServerKletApplication>(*args)
}
