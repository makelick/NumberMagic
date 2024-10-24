package com.makelick.numbermagic.domain

interface NetworkRepository {
    suspend fun getFact(number: Int): Result<Long>
    suspend fun getRandomFact(): Result<Long>
}