package com.nicholas.rutherford.challenger.api.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "splash")
data class Splash(
        @Id @GeneratedValue(
                strategy = GenerationType.IDENTITY) val id: Long = 0,
        @get: NotBlank val name: String = "",
        @get: NotBlank val primary_color: String = "",
        @get: NotBlank val secondary_color: String = "",
        @get: NotBlank val url: String = "",
        @get: NotNull val is_active: Boolean = false){
}