package com.nickrutherford.challenger.api.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "challenge")
data class Challenge(
        @Id @GeneratedValue(
                strategy = GenerationType.IDENTITY) val id: Long = 0,
        @get: NotBlank val title: String = "",
        @get: NotBlank val desc: String = "",
        @get: NotBlank val category: String = "",
        @get: NotBlank val imageUrl: String = "") {
}