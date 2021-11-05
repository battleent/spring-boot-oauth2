package com.example.authorization.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

/**
 * @EnableAuthorizationServer Authorization Server를 구성하는데 필요한 기본 설정을 셋팅한다.
 */
@Configuration
@EnableAuthorizationServer
class Oauth2AuthorizationConfig(
    val dataSource: DataSource
) : AuthorizationServerConfigurerAdapter() {

    /**
     * 토큰 엔드포인트에 대한 보안 제약을 정의한다.
     * - tokenKeyAccess: /oauth/token_key에 대한 허용을 의미하며 jwt 토큰 검증을 위한 공개키 노출
     * - checkTokenAccess: /oauth/check_token에 대한 허용을 의미하며 access token에 포함된 데이터를 복호화하여 출력
     * - allowFormAuthenticationForClients: 클라이언트의 인증 정보를 html의 form data 형태로 받을 수 있게함
     * - password 데이터를 받을 때 password의 encoder를 설정.
     *   spring 5.0 부터는 보안의 이유로 NoOpPasswordEncoder가 deprecated 되었지만,
     *   firebase 로그인시에 password로 사용될 idToken 값이 이미 암호화 되어있기 때문에
     *   해당 프로젝트에서는 NoOpPasswordEncoder를 사용
     * - addTokenEndpointAuthenticationFilter: cors 정책을 지원하기 위해 추가. 현재는 모든 요청을 허용하도록 되어있음.
     */
    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
    }

    /**
     * 클라이언트를 인가할 때 사용되는 client_id와 client_secret을 설정하며 인가 타입과 access token의 expire 시간을 설정합니다.
     */
    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource)
    }

    /**
     * 인가 방법과 토큰 엔드포인트 설정 및 토큰 서비스를 정의합니다.
     * - userDetailsService: 사용자를 인증하고 인가하는 서비스를 설정
     *   refresh token 발급을 위해서는 UserDetailsService(AuthenticationManager authenticate()에서 사용)필요
     * - accessTokenConverter: access token을 jwt 토큰으로 변환하기 위해 사용하며 jwtSecret 키를 통해 jwt 토큰을 설정
     * - tokenStore: token이 저장될 기본 store를 정의
     * - authenticationManager: password 값으로 사용자를 인증하고 인가
     * - allowedTokenEndpointRequestMethods: token endpoint를 사용할 때 허용할 method들을 설정
     * - tokenEnhancer: access token 추가 설정
     */
    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
            .approvalStore(approvalStore())
            .tokenStore(tokenStore())
            .authorizationCodeServices(authorizationCodeServices())
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JdbcTokenStore(dataSource)
    }

    @Bean
    fun authorizationCodeServices(): AuthorizationCodeServices {
        return JdbcAuthorizationCodeServices(dataSource)
    }

    @Bean
    fun approvalStore(): ApprovalStore {
        return JdbcApprovalStore(dataSource)
    }
}
