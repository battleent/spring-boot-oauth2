package com.example.account.entities

import javax.persistence.*

@Entity
@Table(name = "users")
class User : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var username: String
}