package com.thirdparty.controller

import com.thirdparty.data.GetPartnerRequest
import com.thirdparty.data.GetPartnerResponse
import com.thirdparty.service.PartnerService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/partner")
class PartnerController(private val partnerService: PartnerService) {

    @PostMapping("fetch")
    fun getPartner(@Valid @RequestBody request: GetPartnerRequest): GetPartnerResponse {
        return partnerService.fetch(request)
    }

}