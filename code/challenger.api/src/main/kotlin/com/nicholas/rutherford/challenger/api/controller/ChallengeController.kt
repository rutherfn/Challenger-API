package com.nicholas.rutherford.challenger.api.controller

import com.nicholas.rutherford.challenger.api.models.Challenge
import com.nicholas.rutherford.challenger.api.repository.ChallengeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ChallengeController (@Autowired private val challengeRepository: ChallengeRepository) {

    // get all the challenges
    @GetMapping("/challenge")
    fun getAllChallenges() : List<Challenge> = challengeRepository.findAll()

    // creates a challenge
    @PostMapping("/challenge")
    fun createChallenges(@Valid @RequestBody challenge: Challenge) : Challenge = challengeRepository.save(challenge)

    // updates a challenge
    @PutMapping("/challenge/{challengeId}")
    fun updateChallenge(@PathVariable challengeId: Long, @Valid @RequestBody updatedChallenge: Challenge)
                        : ResponseEntity<Challenge> =
                    challengeRepository.findById(challengeId).map {
                        val newChallenge = it.copy(title = updatedChallenge.title, desc = updatedChallenge.desc, category = updatedChallenge.category, imageUrl = updatedChallenge.imageUrl)
                        ResponseEntity.ok().body(challengeRepository.save(newChallenge))
                    }.orElse(ResponseEntity.notFound().build())

    // deletes a challenge
    @DeleteMapping("/challenge/{challengeId}")
    fun deleteChallenge(@PathVariable challengeId: Long): ResponseEntity<Void> =
                            challengeRepository.findById(challengeId).map {
                                challengeRepository.delete(it)
                                ResponseEntity<Void>(HttpStatus.OK)
                            }.orElse(ResponseEntity.notFound().build())
}