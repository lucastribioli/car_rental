package com.example.demo.interfaces

import com.example.demo.domain.Driver
import com.example.demo.domain.DriverRepository
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
import java.time.LocalDate
import java.util.*

@Service
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class DriverAPI(
    var driverRepository: DriverRepository
) {

    @GetMapping("drivers")
    fun listDrivers() = driverRepository.findAll()

    @GetMapping("driver/{id}")
    fun listOneDriver(@PathVariable("id") id: Long) = driverRepository
        .findById(id)
        .orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    @PostMapping("drivers")
    fun createDriver(@RequestBody driver: Driver) = driverRepository.save(driver)

    @PutMapping("driver/{id}")
    fun updateFullDriver(@PathVariable("id") id: Long, @RequestBody driver: Driver): Driver {
        return driverRepository.save(
            driverRepository
                .findById(id)
                .get()
                .copy(
                    birthData = driver.birthData,
                    nome = driver.nome
                )
        )
    }

    @PatchMapping("driver/{id}")
    fun updateDriver(@PathVariable("id") id: Long, @RequestBody driver: PassengerLocal): Driver {
        val driverLocal = driverRepository.findById(id).get()
        return driverRepository.save(
            driverLocal
                .copy(
                    nome = driver.nome ?: driverLocal.nome,
                    birthData = driver.birthData ?: driverLocal.birthData
                )
        )
    }

    @DeleteMapping("driver/{id}")
    fun deleteDriver(@PathVariable("id") id: Long) = driverRepository.delete(
        driverRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) })
}

data class DriverPatch(
    val nome: String?,
    val birthData: LocalDate?
)