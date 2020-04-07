package dev.codesample.springboot.authentication.jwt

import org.springframework.data.repository.CrudRepository

interface AppUserRepository : CrudRepository<AppUser, Long> {

    fun findByUsername(username: String): AppUser?
}
