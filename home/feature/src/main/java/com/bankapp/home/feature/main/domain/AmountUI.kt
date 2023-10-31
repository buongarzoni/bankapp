package com.bankapp.home.feature.main.domain

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.bankapp.components.theme.negativeNumberColor
import com.bankapp.components.theme.positiveNumberColor
import com.bankapp.home.feature.R
import com.bankapp.home.model.domain.Amount


@Composable
fun Amount.ToUI() {
    val isPositive = quantity >= 0
    val color = if (isPositive) positiveNumberColor else negativeNumberColor

    val icon: @Composable () -> Unit = if (isPositive) {
        {
            Icon(
                imageVector = Icons.Outlined.ArrowUpward,
                contentDescription = stringResource(id = R.string.home_feature_cd_positive_amount),
                tint = positiveNumberColor,
            )
        }
    } else {
        {
            Icon(
                imageVector = Icons.Outlined.ArrowDownward,
                contentDescription = stringResource(id = R.string.home_feature_cd_negative_amount),
                tint = negativeNumberColor,
            )
        }
    }

    Row(
        verticalAlignment = Alignment.Bottom,
    ) {
        icon()
        Text(
            text = "$currency ${String.format("%.2f", quantity)}",
            color = color,
            fontWeight = FontWeight.Bold,
        )
    }
}
