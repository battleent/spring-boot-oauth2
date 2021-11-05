package com.example.authorization.entities

import org.hibernate.annotations.BatchSize
import javax.persistence.*

@Entity
@Table(name = "users_authorities",
    uniqueConstraints = [
        UniqueConstraint(name = "udx_UserId_AuthorityId", columnNames = ["user_id", "authority_id"])
    ],
    indexes = [
        Index(name = "idx_CreatedAt", columnList = "created_at"),
        Index(name = "idx_UpdatedAt", columnList = "updated_at"),
        Index(name = "idx_AuthorityId", columnList = "authority_id")
    ]
)
@BatchSize(size = 100)
class UserAuthority : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn
    lateinit var user: User

    @ManyToOne
    @JoinColumn
    lateinit var authority: Authority
}