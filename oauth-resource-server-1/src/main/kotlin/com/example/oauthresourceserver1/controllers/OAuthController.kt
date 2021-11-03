package com.example.oauthresourceserver1.controllers

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate


@RestController
@RequestMapping("/oauth2")
class OAuthController(
    val restTemplate: RestTemplate
) {

    @GetMapping("/callback")
    fun code(@RequestParam code: String): OauthToken? {
        val headers = HttpHeaders().apply {
            this.contentType = MediaType.APPLICATION_FORM_URLENCODED
            this.setBasicAuth("client1", "pass")
        }

        val data = LinkedMultiValueMap<String, String>()
        data.add("code", code)
        data.add("grant_type", "authorization_code")
        data.add("redirect_uri", "http://localhost:9091/oauth2/callback")

        val entity = HttpEntity(data, headers)
        return restTemplate.postForEntity("http://localhost:5000/oauth/token", entity, OauthToken::class.java).body
    }
}