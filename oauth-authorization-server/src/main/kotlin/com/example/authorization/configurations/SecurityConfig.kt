package com.example.authorization.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val userDetailService: CustomUserDetailService
) : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailService)
    }

    @Throws(Exception::class)
    override fun configure(security: HttpSecurity) {
        security
            .authorizeRequests()
            .antMatchers("/oauth/**", "/h2-console/*").permitAll()
            .and()
            .formLogin().loginPage("/login").and()
            .httpBasic()
    }

//    @Throws(Exception::class)
//    override fun configure(web: WebSecurity) {
//        web.ignoring().antMatchers("/h2-console/**")
//    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
