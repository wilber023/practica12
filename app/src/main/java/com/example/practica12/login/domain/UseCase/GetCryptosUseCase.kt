package com.example.practica12.login.domain.UseCase

import com.example.cryptofinder.data.model.CryptoListItem
import com.example.practica12.login.domain.repository.CryptoRepository

class GetCryptosUseCase(
    private val repository: CryptoRepository
) {
    suspend operator fun invoke(): List<CryptoListItem> = repository.fetchAllCryptos()
}