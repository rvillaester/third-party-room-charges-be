package com.thirdparty.controller

import com.thirdparty.data.CreateWalletRequest
import com.thirdparty.data.CreateWalletResponse
import com.thirdparty.service.WalletService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/wallet")
class WalletController(private val service: WalletService) {

    @PostMapping("create")
    fun create(@Valid @RequestBody request: CreateWalletRequest?): CreateWalletResponse? {
        return service.create(request!!)
    }
}