package com.example.klet.responses

import com.fasterxml.jackson.annotation.JsonProperty

class AccessTokenResponse {

    @JsonProperty("access_token")
    lateinit var accessToken: String

    @JsonProperty("token_type")
    lateinit var tokenType: String

    @JsonProperty("refresh_token")
    lateinit var refreshToken: String

    @JsonProperty("expires_in")
    var expiresIn: Long = 0

    @JsonProperty("scope")
    lateinit var scope: String

    var customInfo: String? = null
}