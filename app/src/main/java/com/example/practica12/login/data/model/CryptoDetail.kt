package com.example.practica12.login.data.model

import com.google.gson.annotations.SerializedName

data class CryptoDetail(
    val id: String,
    val symbol: String,
    val name: String,
    @SerializedName("market_data")
    val marketData: MarketData?
)

data class MarketData(
    @SerializedName("current_price")
    val currentPrice: CurrentPrice?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?,
    @SerializedName("market_cap")
    val marketCap: MarketCap?
)

data class CurrentPrice(
    val usd: Double?
)

data class MarketCap(
    val usd: Long?
)