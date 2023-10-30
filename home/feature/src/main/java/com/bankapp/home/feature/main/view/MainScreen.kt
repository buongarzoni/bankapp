package com.bankapp.home.feature.main.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bankapp.components.screen.BankTopAppBar
import com.bankapp.home.feature.main.presentation.MainPresenter

@Composable
fun MainScreen(
    presenter: MainPresenter,
) = Scaffold(
    topBar = { BankTopAppBar() }
) {
    Content(Modifier.padding(it), presenter)
}

@Composable
private fun Content(
    modifier: Modifier,
    presenter: MainPresenter,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .padding(top = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(text = "main screen")
        }
    }
}
