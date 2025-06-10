package com.example.practica12.login.domain.repository

import com.example.cryptofinder.data.model.CryptoListItem
import com.example.practica12.login.data.model.CryptoDetail

interface CryptoRepository {
    suspend fun fetchAllCryptos(): List<CryptoListItem>
    suspend fun fetchCryptoDetail(id: String): CryptoDetail
}