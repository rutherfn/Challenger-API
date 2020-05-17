package com.nicholas.rutherford.challenger.api.controller

import com.nicholas.rutherford.challenger.api.models.Onboarding
import com.nicholas.rutherford.challenger.api.repository.OnboardingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/challenger-api")
class OnboardingController (@Autowired private val onboardingRepository: OnboardingRepository) {

    // get all of the onboarding screens
    @GetMapping("all/onboarding")
    fun getAllOnboarding(): List<Onboarding> = onboardingRepository.findAll()

    // get a onboarding screen by name
    @GetMapping("onboarding/{title}")
    fun getOnboardingByName(@PathVariable title: String)
            : Onboarding = onboardingRepository.findOnboardingByTitle(title)

    // get a onboarding screen by onboarding number
    @GetMapping("onboarding/number/{number}")
    fun getOnboardingByNumber(@PathVariable number: Int)
            : Onboarding = onboardingRepository.findOnboardingByNumber(number)

    // create onboarding
    @PostMapping("create/onboarding")
    fun createOnboarding(@Valid @RequestBody onboarding: Onboarding) : Onboarding = onboardingRepository.save(onboarding)

    // update onboarding
    @PutMapping("update/onboarding/{id}")
    fun updateOnboarding(@PathVariable id: Long, @Valid @RequestBody updatedOnboarding: Onboarding) :
            ResponseEntity<Onboarding> =
            onboardingRepository.findById(id).map {
                val newOnboarding = it.copy(title =  updatedOnboarding.title, sub = updatedOnboarding.sub, body = updatedOnboarding.body,
                        url = updatedOnboarding.url, number = updatedOnboarding.number)
                ResponseEntity.ok().body(onboardingRepository.save(newOnboarding))
            }.orElse(ResponseEntity.notFound().build())

    // delete onboarding
    @DeleteMapping("onboarding-delete/{id}")
    fun deleteOnboarding(@PathVariable id: Long): ResponseEntity<Void> =
            onboardingRepository.findById(id).map {
                onboardingRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())

}