package com.example.authorization.properties

import com.example.authorization.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(email: String?): User?
}