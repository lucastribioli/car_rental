package com.example.demo.interfaces

import com.example.demo.domain.Passenger
import com.example.demo.domain.PassengerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class PassengerAPI(
    var passengerDriver: PassengerRepository
) {

    @GetMapping("drivers")
    fun listDrivers() = passengerDriver.findAll()

    @GetMapping("driver/{id}")
    fun listOneDriver(@PathVariable("id") id: Long) = passengerDriver
        .findById(id)
        .orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    @PostMapping("drivers")
    fun createDriver(@RequestBody passenger: Passenger) = passengerDriver.save(passenger)

    @PutMapping("driver/{id}")
    fun updateFullDriver(@PathVariable("id") id: Long, @RequestBody driver: Passenger): Passenger {
        return passengerDriver.save(
            passengerDriver
                .findById(id)
                .get()
                .copy(
                    nome = driver.nome
                )
        )
    }

    @PatchMapping("driver/{id}")
    fun updateDriver(@PathVariable("id") id: Long, @RequestBody passenger: PassengerLocal): Passenger {
        val driverLocal = passengerDriver.findById(id).get()
        return passengerDriver.save(
            driverLocal
                .copy(
                    nome = passenger.nome ?: driverLocal.nome,
                )
        )
    }

    @DeleteMapping("driver/{id}")
    fun deleteDriver(@PathVariable("id") id: Long) = passengerDriver.delete(
        passengerDriver.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) })
}

data class PassengerLocal(
    val nome: String?
)