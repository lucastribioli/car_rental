package com.example.demo.domain

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

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

@Entity
data class TravelRequest(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    var passenger: Passenger,
    var origin: String,
    var destination: String
)
