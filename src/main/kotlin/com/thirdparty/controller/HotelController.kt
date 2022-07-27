package com.thirdparty.controller

import com.thirdparty.data.GetHotelResponse
import com.thirdparty.service.HotelService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hotel")
class HotelController(private val service: HotelService) {

    @GetMapping("{hotelId}")
    fun getHotel(@PathVariable hotelId: String): GetHotelResponse {
        return service.fetch(hotelId)
    }

}