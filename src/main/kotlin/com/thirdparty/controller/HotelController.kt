package com.thirdparty.controller

import com.thirdparty.data.GetHotelRequest
import com.thirdparty.data.GetHotelResponse
import com.thirdparty.service.HotelService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hotel")
class HotelController(private val service: HotelService) {

    @PostMapping("fetch")
    fun getHotels(@Valid @RequestBody request: GetHotelRequest): GetHotelResponse {
        return service.fetch(request)
    }

}