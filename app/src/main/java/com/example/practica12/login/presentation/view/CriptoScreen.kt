package com.example.cryptofinder.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.cryptofinder.data.model.CryptoListItem
import com.example.practica12.login.data.model.CryptoDetail
import com.example.practica12.login.presentation.viewmodel.CryptoViewModel
import com.example.practica12.login.di.DependencyContainer
import java.text.NumberFormat
import java.util.*

@Composable
fun CryptoScreen() {
    val viewModel: CryptoViewModel = viewModel(
        factory = DependencyContainer.cryptoViewModelFactory
    )

    CryptoScreenContent(
        query = viewModel.query,
        onQueryChange = viewModel::onQueryChange,
        items = viewModel.filteredCryptos,
        selectedItem = viewModel.selectedCrypto,
        onItemClick = viewModel::selectCrypto,
        onBack = viewModel::clearSelection,
        isLoading = viewModel.isLoading
    )
}

@Composable
private fun CryptoScreenContent(
    query: TextFieldValue = TextFieldValue(""),
    onQueryChange: (TextFieldValue) -> Unit = {},
    items: List<CryptoListItem> = emptyList(),
    selectedItem: CryptoDetail? = null,
    onItemClick: (CryptoListItem) -> Unit = {},
    onBack: () -> Unit = {},
    isLoading: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB8E0CF))
            .padding(16.dp)
    ) {
        Text(
            text = "CryptoFinder",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (selectedItem != null) {
            CryptoDetailScreen(
                crypto = selectedItem,
                onBack = onBack
            )
        } else {

            CryptoListScreen(
                query = query,
                onQueryChange = onQueryChange,
                items = items,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
private fun CryptoDetailScreen(
    crypto: CryptoDetail,
    onBack: () -> Unit
) {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    val price = crypto.marketData?.currentPrice?.usd ?: 0.0
    val change = crypto.marketData?.priceChangePercentage24h ?: 0.0
    val marketCap = crypto.marketData?.marketCap?.usd ?: 0L

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = crypto.name,
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = "Símbolo: ${crypto.symbol.uppercase()}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Precio Actual: $${numberFormat.format(price)}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Cambio 24h: ${String.format("%.2f", change)}%",
                    color = if (change >= 0) Color(0xFF008000) else Color(0xFFFF0000)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Capitalización: $${numberFormat.format(marketCap)}")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "← Volver",
            modifier = Modifier
                .clickable { onBack() }
                .padding(8.dp),
            style = TextStyle(color = Color(0xFF555555), fontSize = 16.sp)
        )
    }
}

@Composable
private fun CryptoListScreen(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    items: List<CryptoListItem>,
    onItemClick: (CryptoListItem) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
            modifier = Modifier
                .padding(12.dp),
            decorationBox = { inner ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)

                ) {
                    if (query.text.isEmpty()) {
                        Text(
                            text = "Buscar cripto...",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                    inner()
                }
            }
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onItemClick(item) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD),
                    contentColor = Color(0xFF0D47A1)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),

            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${item.name} (${item.symbol.uppercase()})",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A237E)
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "ID: ${item.id}",
                        style = TextStyle(fontSize = 14.sp, color = Color(0xFF546E7A))
                    )
                }
            }

        }
    }
}