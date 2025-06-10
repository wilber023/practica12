package com.example.practica12.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practica12.login.domain.UseCase.GetCryptoDetailUseCase
import com.example.practica12.login.domain.UseCase.GetCryptosUseCase

class CryptoViewModelFactory(
    private val getCryptosUseCase: GetCryptosUseCase,
    private val getCryptoDetailUseCase: GetCryptoDetailUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoViewModel::class.java)) {
            return CryptoViewModel(getCryptosUseCase, getCryptoDetailUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}