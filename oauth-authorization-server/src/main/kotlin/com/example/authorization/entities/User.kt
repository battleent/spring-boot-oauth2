package com.example.authorization.entities

import javax.persistence.*

@Entity
@Table(name = "users")
class User : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var username: String

    @Column(columnDefinition = "CHAR(60) NOT NULL")
    lateinit var password: String

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "user_id", updatable = false)
    var authorities: MutableSet<UserAuthority> = mutableSetOf()
}