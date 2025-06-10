package com.example.practica12.login.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.example.cryptofinder.data.model.CryptoListItem
import com.example.practica12.login.data.model.CryptoDetail
import com.example.practica12.login.domain.UseCase.GetCryptosUseCase
import com.example.practica12.login.domain.UseCase.GetCryptoDetailUseCase

class CryptoViewModel(
    private val getCryptosUseCase: GetCryptosUseCase,
    private val getCryptoDetailUseCase: GetCryptoDetailUseCase
) : ViewModel() {

    var cryptos by mutableStateOf<List<CryptoListItem>>(emptyList())
        private set

    var filteredCryptos by mutableStateOf<List<CryptoListItem>>(emptyList())
        private set

    var selectedCrypto by mutableStateOf<CryptoDetail?>(null)
        private set

    var query by mutableStateOf(TextFieldValue(""))
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadCryptos()
    }

    fun loadCryptos() {
        viewModelScope.launch {
            isLoading = true
            try {
                cryptos = getCryptosUseCase()
                filteredCryptos = cryptos
            } catch (e: Exception) {

            } finally {
                isLoading = false
            }
        }
    }

    fun selectCrypto(crypto: CryptoListItem) {
        viewModelScope.launch {
            isLoading = true
            try {
                selectedCrypto = getCryptoDetailUseCase(crypto.id)
            } catch (e: Exception) {

            } finally {
                isLoading = false
            }
        }
    }

    fun clearSelection() {
        selectedCrypto = null
    }

    fun onQueryChange(newQuery: TextFieldValue) {
        query = newQuery
        filteredCryptos = if (newQuery.text.isEmpty()) {
            cryptos
        } else {
            cryptos.filter {
                it.name.contains(newQuery.text, ignoreCase = true) ||
                        it.symbol.contains(newQuery.text, ignoreCase = true)
            }
        }
    }
}