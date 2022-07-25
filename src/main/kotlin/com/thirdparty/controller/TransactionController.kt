package com.thirdparty.controller

import com.thirdparty.data.CreateTransactionRequest
import com.thirdparty.service.TransactionService
import com.thirdparty.data.GetTransactionRequest
import com.thirdparty.data.GetTransactionResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction")
class TransactionController(private val service: TransactionService) {

    @PostMapping("create")
    fun create(@RequestHeader("partner-key") partnerKey: String?, @Valid @RequestBody request: CreateTransactionRequest): String? {
        return "Save transaction to Dynamo DB and returns reference number"
    }

    @PostMapping("fetch", consumes = ["application/json"], produces = ["application/json"])
    fun fetch(@RequestHeader("partner-key") partnerKey: String?, @Valid @RequestBody request: GetTransactionRequest): GetTransactionResponse? {
        return service.fetch()
    }
}