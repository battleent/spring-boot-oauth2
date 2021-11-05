package com.example.authorization.configurations

import com.example.authorization.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

class UserPrincipal(
    private var user: User
) : UserDetails, Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()

    override fun getUsername(): String = user.username

    override fun getPassword(): String = user.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
