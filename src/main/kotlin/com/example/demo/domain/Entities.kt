package com.example.demo.domain

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Driver(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val nome: String,
    val birthData: LocalDate
)

@Entity
data class Passenger(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val nome: String
)
