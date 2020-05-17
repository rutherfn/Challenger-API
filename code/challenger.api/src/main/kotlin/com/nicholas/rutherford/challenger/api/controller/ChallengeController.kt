package com.nicholas.rutherford.challenger.api.controller

import com.nicholas.rutherford.challenger.api.models.Challenge
import com.nicholas.rutherford.challenger.api.repository.ChallengeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/challenger-api")
class ChallengeController (@Autowired private val challengeRepository: ChallengeRepository) {

    // get all the challenges
    @GetMapping("all/challenges")
    fun getAllChallenges() : List<Challenge> = challengeRepository.findAll()

    // get a challenge by a name
    @GetMapping("challenge/{title}")
    fun getChallengeByName(@PathVariable title: String)
            : Challenge = challengeRepository.findChallengeByName(title)

    // gets all challenges by a certain category
    @GetMapping("challenges/category/{category}")
    fun getChallengesByCategory(@PathVariable category: String)
            : List<Challenge> = challengeRepository.findChallengesByCategory(category)

    // creates a challenge
    @PostMapping("create/challenge")
    fun createChallenges(@Valid @RequestBody challenge: Challenge) : Challenge = challengeRepository.save(challenge)

    // updates a challenge
    @PutMapping("update/challenge/{challengeId}")
    fun updateChallenge(@PathVariable challengeId: Long, @Valid @RequestBody updatedChallenge: Challenge)
            : ResponseEntity<Challenge> =
            challengeRepository.findById(challengeId).map {
                val newChallenge = it.copy(title = updatedChallenge.title, body = updatedChallenge.body, category = updatedChallenge.category, url = updatedChallenge.url)
                ResponseEntity.ok().body(challengeRepository.save(newChallenge))
            }.orElse(ResponseEntity.notFound().build())

    // deletes a challenge
    @DeleteMapping("challenge-delete/{challengeId}")
    fun deleteChallenge(@PathVariable challengeId: Long): ResponseEntity<Void> =
            challengeRepository.findById(challengeId).map {
                challengeRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())
}