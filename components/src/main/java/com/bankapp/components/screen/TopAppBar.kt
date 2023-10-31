@file:OptIn(ExperimentalMaterial3Api::class)

package com.bankapp.components.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bankapp.components.R

@Composable
fun BankTopAppBar() = TopAppBar(
    title = { Text(stringResource(id = R.string.components_top_app_bar_app_name)) },
)
