package com.example.practica12.login.di

import com.example.cryptofinder.data.remote.CoinGeckoApi
import com.example.cryptofinder.data.repository.CryptoRepositoryImpl
import com.example.cryptofinder.network.NetworkModule
import com.example.practica12.login.domain.UseCase.GetCryptoDetailUseCase
import com.example.practica12.login.domain.UseCase.GetCryptosUseCase
import com.example.practica12.login.presentation.viewmodel.CryptoViewModelFactory

object DependencyContainer {

    private val coinGeckoApi: CoinGeckoApi = NetworkModule.retrofit.create(CoinGeckoApi::class.java)

    private val cryptoRepository: CryptoRepositoryImpl = CryptoRepositoryImpl(coinGeckoApi)

    private val getCryptosUseCase: GetCryptosUseCase = GetCryptosUseCase(cryptoRepository)
    private val getCryptoDetailUseCase: GetCryptoDetailUseCase = GetCryptoDetailUseCase(cryptoRepository)

    val cryptoViewModelFactory: CryptoViewModelFactory = CryptoViewModelFactory(
        getCryptosUseCase,
        getCryptoDetailUseCase
    )
}