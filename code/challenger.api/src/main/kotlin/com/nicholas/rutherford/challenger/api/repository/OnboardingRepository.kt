package com.nicholas.rutherford.challenger.api.repository

import com.nicholas.rutherford.challenger.api.models.Onboarding
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OnboardingRepository: JpaRepository<Onboarding, Long> {
    @Query("select b FROM onboarding b WHERE b.title = :title")
    fun findOnboardingByTitle(@Param("title") title: String) :
            Onboarding

    @Query("select b from onboarding b WHERE b.onboardingNumber = :onboardingNumber")
    fun findOnboardingByNumber(@Param("onboardingNumber") onboardingNumber: Int) :
            Onboarding
}