package com.example.cryptofinder.data.remote


import com.example.cryptofinder.data.model.CryptoListItem
import com.example.practica12.login.data.model.CryptoDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/list")
    suspend fun getAllCryptos(): List<CryptoListItem>

    @GET("coins/{id}")
    suspend fun getCryptoDetail(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false
    ): CryptoDetail
}