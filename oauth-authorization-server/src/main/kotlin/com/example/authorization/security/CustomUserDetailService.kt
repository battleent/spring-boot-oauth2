package com.example.authorization.security

import com.example.authorization.properties.UserRepository
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    val userRepository: UserRepository
) : UserDetailsService {

    private val detailsChecker = AccountStatusUserDetailsChecker()

    override fun loadUserByUsername(name: String): UserDetails {
        val user = userRepository.findByUsername(name)
            ?: throw UsernameNotFoundException("user is not exists")

        detailsChecker.check(user)
        
        return user
    }
}