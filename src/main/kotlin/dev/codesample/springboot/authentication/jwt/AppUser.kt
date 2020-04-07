package dev.codesample.springboot.authentication.jwt

import javax.persistence.*

@Entity
class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(unique = true)
    var username: String? = null
    var password: String? = null
    var firstName: String? = null
    var lastName: String? = null
}
