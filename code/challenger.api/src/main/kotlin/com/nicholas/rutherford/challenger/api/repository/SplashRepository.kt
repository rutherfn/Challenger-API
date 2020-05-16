package com.nicholas.rutherford.challenger.api.repository

import com.nicholas.rutherford.challenger.api.models.Splash
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SplashRepository : JpaRepository<Splash, Long>{
}