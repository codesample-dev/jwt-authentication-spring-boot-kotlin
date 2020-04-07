package dev.codesample.springboot.authentication.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
class AppController @Autowired constructor(private val userRepository: AppUserRepository,
                                           private val passwordEncoder: BCryptPasswordEncoder) {

    @GetMapping("/register")
    fun getRegistrationPage(): String {
        return "register"
    }

    @PostMapping("/register")
    fun register(@RequestParam username: String, @RequestParam password: String,
                 @RequestParam firstName: String, @RequestParam lastName: String) {
        // When the register form is submitted, save the user information into the database.
        val user = AppUser()
        user.username = username
        user.password = passwordEncoder.encode(password)
        user.firstName = firstName
        user.lastName = lastName
        userRepository.save(user)
    }

    @GetMapping("/login")
    fun getLoginPage(): String {
        return "login"
    }

    @GetMapping("/dashboard")
    fun getDashboardPage(authentication: Authentication): ModelAndView {
        // For the dashboard, just display user information.
        val username = authentication.name
        val user = userRepository.findByUsername(username)
        return ModelAndView("dashboard", mapOf("user" to user))
    }
}
