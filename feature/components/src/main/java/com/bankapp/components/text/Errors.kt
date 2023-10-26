package com.bankapp.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextFieldError(
    text: String,
) = Text(
    text = text,
    modifier = Modifier.fillMaxWidth(),
    color = MaterialTheme.colorScheme.error,
)
