package com.example.authorization.configurations

import com.example.authorization.entities.User
import com.example.authorization.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(name: String): UserDetails =
        userRepository.findByUsername(name)?.let { createUser(it) }
            ?: throw UsernameNotFoundException("user is not exists")

    private fun createUser(user: User) = UserPrincipal(user)
}