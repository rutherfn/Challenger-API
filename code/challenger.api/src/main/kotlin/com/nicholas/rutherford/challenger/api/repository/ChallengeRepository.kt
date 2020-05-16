package com.nicholas.rutherford.challenger.api.repository

import com.nicholas.rutherford.challenger.api.models.Challenge
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChallengeRepository: JpaRepository<Challenge, Long>{
}