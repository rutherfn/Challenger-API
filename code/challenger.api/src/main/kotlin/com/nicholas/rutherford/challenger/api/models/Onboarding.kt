package com.nicholas.rutherford.challenger.api.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "onboarding")
data class Onboarding(
        @Id @GeneratedValue(
                strategy = GenerationType.IDENTITY) val id: Long = 0,
        @get: NotBlank val title: String = "",
        @get: NotBlank val sub: String = "",
        @get: NotBlank val body: String = "",
        @get: NotBlank val url: String = "",
        @get: NotNull val number: Int = 0) {
}