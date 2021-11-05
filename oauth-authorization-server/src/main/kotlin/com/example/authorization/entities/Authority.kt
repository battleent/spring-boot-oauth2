package com.example.authorization.entities

import com.example.authorization.enums.AuthorityName
import org.hibernate.annotations.BatchSize
import javax.persistence.*

@Entity
@Table(name = "authorities")
@BatchSize(size = 100)
class Authority(
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    var roleName: AuthorityName
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
