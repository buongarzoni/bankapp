package com.bankapp.components.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bankapp.components.R

private const val CONTENT_ANIMATION_DURATION = 250

@Composable
fun <S> AnimatedSlide(
    targetState: S,
    slideRightIf: (S) -> Boolean,
    content: @Composable() (AnimatedContentScope.(S) -> Unit)
) {
    AnimatedContent(
        targetState = targetState,
        label = stringResource(id = R.string.components_label_animated_slide_transition),
        transitionSpec = {
            if (slideRightIf(targetState)) {
                slideInHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    initialOffsetX = { fullWidth -> -fullWidth }
                ) togetherWith slideOutHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            } else {
                slideInHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    initialOffsetX = { fullWidth -> fullWidth }
                ) togetherWith slideOutHorizontally(
                    animationSpec = tween(CONTENT_ANIMATION_DURATION),
                    targetOffsetX = { fullWidth -> -fullWidth }
                )
            }
        }
    ) {
        content(it)
    }
}
