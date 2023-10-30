package com.bankapp.components.lotties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bankapp.components.R

@Composable
fun LottieSuccess(
    modifier: Modifier,
) {
    val composition = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_success))
    LottieAnimation(
        modifier = modifier,
        composition = composition.value,
    )
}
