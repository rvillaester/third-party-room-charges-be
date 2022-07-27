package com.thirdparty.controller

import com.thirdparty.data.GetPartnerResponse
import com.thirdparty.service.PartnerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/partner")
class PartnerController(private val partnerService: PartnerService) {

    @GetMapping("{partnerId}")
    fun getPartner(@PathVariable partnerId: String): GetPartnerResponse {
        return partnerService.fetch(partnerId)
    }

}