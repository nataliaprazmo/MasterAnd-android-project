package com.example.masterand.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

object AnimationAndTransitionUtils {
    private fun determineDirection(initialRoute: String?, targetRoute: String?): Boolean {
        val screensOrder = listOf(Constants.PROFILE, Constants.GAME, Constants.RESULT)
        val fromIndex = screensOrder.indexOf(initialRoute)
        val toIndex = screensOrder.indexOf(targetRoute)
        return toIndex > fromIndex
    }

    fun slideInTransition(initialRoute: String?, targetRoute: String?): EnterTransition {
        return fadeIn() + slideInHorizontally(
            initialOffsetX = { if (determineDirection(initialRoute, targetRoute)) it else -it },
            animationSpec = tween(
                durationMillis = 400,
                easing = EaseIn
            )
        )
    }

    fun slideOutTransition(initialRoute: String?, targetRoute: String?): ExitTransition {
        return fadeOut() + slideOutHorizontally(
            targetOffsetX = { if (determineDirection(initialRoute, targetRoute)) -it else it },
            animationSpec = tween(
                durationMillis = 400,
                easing = EaseOut
            )
        )
    }

    @Composable
    fun AnimateScale(
        initialValue: Float = 1f,
        targetValue: Float = 1.2f,
        durationMillis: Int = 1000,
        content: @Composable (Float) -> Unit
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "scale-animation")
        val scale by infiniteTransition.animateFloat(
            initialValue = initialValue,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = durationMillis, easing = LinearEasing),
                RepeatMode.Reverse
            ),
            label = "scale-animation"
        )

        content(scale)
    }

    @Composable
    fun AnimateColor(color: Color, content: @Composable (Color) -> Unit) {
        val animatedColor by animateColorAsState(
            targetValue = color,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            ),
//            animationSpec = repeatable(
//                iterations = 5,
//                animation = tween(durationMillis = 200),
//                repeatMode = RepeatMode.Reverse
//            ),
            label = "color animation"
        )
        content(animatedColor)
    }

    @Composable
    fun AnimateExpandFromTop(isVisible: Boolean, content: @Composable () -> Unit) {
        AnimatedVisibility(
            visible = isVisible,
            enter = expandVertically(
                expandFrom = Alignment.Top,
                animationSpec = tween(300)
            ) + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            content()
        }
    }

    @Composable
    fun AnimateButton(isEnabled: Boolean, content: @Composable () -> Unit) {
        AnimatedVisibility(
            visible = isEnabled,
            enter = scaleIn(animationSpec = tween(durationMillis = 300, easing = EaseIn)),
            exit = scaleOut(animationSpec = tween(durationMillis = 300, easing = EaseOut))
        ) { content() }
    }

    @Composable
    fun AnimatedFeedbackCircles(
        colors: List<Color>,
        content: @Composable (List<Color>) -> Unit
    ) {
        val colorStates = remember { colors.map { mutableStateOf(it) } }
        LaunchedEffect(colors) {
            colors.forEachIndexed { index, color ->
                delay(index * 200L)
                colorStates[index].value = color
            }
        }
        content(colorStates.map { it.value })
    }

    @Composable
    fun AnimateSmallCircle(
        color: Color,
        content: @Composable (Color) -> Unit
    ) {
        val animatedColor by animateColorAsState(
            targetValue = color,
            animationSpec = tween(durationMillis = 100),
            label = "circle-color-transition"
        )
        content(animatedColor)
    }
}