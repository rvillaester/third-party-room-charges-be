package com.thirdparty.controller

import com.thirdparty.data.LoginRequest
import com.thirdparty.data.LoginResponse
import com.thirdparty.service.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val service: AuthService) {

    @PostMapping("login")
    fun login(@Valid @RequestBody request: LoginRequest?): LoginResponse? {
        return service.login(request!!)
    }
}