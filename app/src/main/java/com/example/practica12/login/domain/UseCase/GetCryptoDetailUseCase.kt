package com.example.practica12.login.domain.UseCase

import com.example.practica12.login.data.model.CryptoDetail
import com.example.practica12.login.domain.repository.CryptoRepository

class GetCryptoDetailUseCase(
    private val repository: CryptoRepository
) {
    suspend operator fun invoke(id: String): CryptoDetail = repository.fetchCryptoDetail(id)
}