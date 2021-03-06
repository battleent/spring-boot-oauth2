//package com.example.authorization.configurations
//
//import com.example.authorization.repositories.UserRepository
//import org.springframework.security.authentication.AuthenticationProvider
//import org.springframework.security.authentication.BadCredentialsException
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.stereotype.Component
//
//@Component
//class CustomAuthenticationProvider(
//    val userRepository: UserRepository
//) : AuthenticationProvider {
//
//    override fun authenticate(authentication: Authentication): Authentication {
//        val name = authentication.name
//        val password = authentication.credentials.toString()
//        val user = userRepository.findByUsername(name)
//            ?: throw UsernameNotFoundException("user is not exists")
//        if (!BCryptPasswordEncoder().matches(password, user.password))
//            throw BadCredentialsException("password is not valid")
//
//        return UsernamePasswordAuthenticationToken(
//            name,
//            password,
////            user.authorities.map { SimpleGrantedAuthority(it.authority.roleName.name) }
//            listOf(SimpleGrantedAuthority("CustomAuthenticationProvider"))
//        )
//    }
//
//    override fun supports(authentication: Class<*>): Boolean {
//        return authentication == UsernamePasswordAuthenticationToken::class.java
//    }
//}
