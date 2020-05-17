package com.nicholas.rutherford.challenger.api.controller

import com.nicholas.rutherford.challenger.api.models.Splash
import com.nicholas.rutherford.challenger.api.repository.SplashRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/challenger-api")
class SplashController (@Autowired private val splashRepository: SplashRepository) {

    // get all of the splash
    @GetMapping("all/splash")
    fun getAllSplash() : List<Splash> = splashRepository.findAll()

    // create a splash
    @PostMapping("create/splash")
    fun createSplash(@Valid @RequestBody splash: Splash): Splash = splashRepository.save(splash)

    // updates a splash
    @PutMapping("update/splash/{id}")
    fun updateSplash(@PathVariable id: Long, @Valid @RequestBody updatedSplash: Splash) :
            ResponseEntity<Splash> =
            splashRepository.findById(id).map {
                val newSplash = it.copy(name = updatedSplash.name, primary_color = updatedSplash.primary_color, secondary_color = updatedSplash.secondary_color,
                        url = updatedSplash.url, is_active = updatedSplash.is_active)
                ResponseEntity.ok().body(splashRepository.save(newSplash))
            }.orElse(ResponseEntity.notFound().build())

    // deletes a splash
    @DeleteMapping("splash-delete/{id}")
    fun deleteSplash(@PathVariable id: Long) : ResponseEntity<Void> =
            splashRepository.findById(id).map {
                splashRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())
}