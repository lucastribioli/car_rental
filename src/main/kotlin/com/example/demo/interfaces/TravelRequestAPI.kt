package com.example.demo.interfaces

import com.example.demo.domain.TravelRequest
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
@RestController
@RequestMapping(path = ["/travelRequest"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TravelRequestAPI {

    @PostMapping
    fun makeRequestTravel(@RequestBody travelRequest: TravelRequest){

    }
}