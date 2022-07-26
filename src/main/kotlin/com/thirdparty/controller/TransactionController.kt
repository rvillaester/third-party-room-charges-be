package com.thirdparty.controller

import com.thirdparty.data.CreateTransactionRequest
import com.thirdparty.data.CreateTransactionResponse
import com.thirdparty.data.GetTransactionRequest
import com.thirdparty.data.GetTransactionResponse
import com.thirdparty.service.TransactionService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(private val service: TransactionService) {
    @PostMapping("create")
    fun create(@Valid @RequestBody request: CreateTransactionRequest?): CreateTransactionResponse? {
        return service.create(request!!)
    }

    @PostMapping("fetch", consumes = ["application/json"], produces = ["application/json"])
    fun fetch(@Valid @RequestBody request: GetTransactionRequest?): GetTransactionResponse? {
        return service.fetch(request!!)
    }
}