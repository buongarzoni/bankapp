@file:OptIn(ExperimentalMaterial3Api::class)

package com.bankapp.components.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun BankTopAppBar() = TopAppBar(
    title = { Text("Bank app") },
)
