package com.makelick.numbermagic.data.repository

import com.makelick.numbermagic.data.local.HistoryDao
import com.makelick.numbermagic.data.local.HistoryItem
import com.makelick.numbermagic.data.remote.NumbersApi
import com.makelick.numbermagic.domain.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: NumbersApi,
    private val historyDao: HistoryDao
) : NetworkRepository {
    override suspend fun getFact(number: Int): Result<Long> {
        try {
            return Result.success(saveFact(number, api.getNumberFact(number)))
        } catch (it: Exception) {
            it.printStackTrace()
            return Result.failure(it)
        }
    }

    override suspend fun getRandomFact(): Result<Long> {
        try {
            val fact = api.getRandomFact()
            return Result.success(saveFact(parseNumberFromFact(fact), fact))
        } catch (it: Exception) {
            it.printStackTrace()
            return Result.failure(it)
        }
    }

    private fun parseNumberFromFact(fact: String): Int {
        return fact.substringBefore(" ").toInt()
    }

    private suspend fun saveFact(number: Int, fact: String): Long {
        return historyDao.insertItem(HistoryItem(0, number, fact))
    }
}