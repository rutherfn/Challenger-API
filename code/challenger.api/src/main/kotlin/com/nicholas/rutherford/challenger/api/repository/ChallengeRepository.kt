package com.nicholas.rutherford.challenger.api.repository

import com.nicholas.rutherford.challenger.api.models.Challenge
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ChallengeRepository: JpaRepository<Challenge, Long>{
    @Query("select c FROM challenge c WHERE c.title = :title")
    fun findChallengeByName(@Param("title") title: String) :
            Challenge

    @Query("select c FROM challenge c WHERE c.category = :category")
    fun findChallengesByCategory(@Param("category") category: String) :
            List<Challenge>
}