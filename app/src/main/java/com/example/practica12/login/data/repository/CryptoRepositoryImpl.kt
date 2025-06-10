package com.example.cryptofinder.data.repository

import com.example.cryptofinder.data.model.CryptoListItem
import com.example.cryptofinder.data.remote.CoinGeckoApi
import com.example.practica12.login.data.model.CryptoDetail
import com.example.practica12.login.domain.repository.CryptoRepository

class CryptoRepositoryImpl(
    private val api: CoinGeckoApi
) : CryptoRepository {
    override suspend fun fetchAllCryptos(): List<CryptoListItem> = api.getAllCryptos()
    override suspend fun fetchCryptoDetail(id: String): CryptoDetail = api.getCryptoDetail(id)
}