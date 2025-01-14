package com.example.masterand.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.masterand.ui.screens.GameScreen
import com.example.masterand.ui.screens.ProfileScreen
import com.example.masterand.ui.screens.ResultScreen
import com.example.masterand.utils.Routes

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    val towardsLeft = AnimatedContentTransitionScope.SlideDirection.Start
    val towardsRight = AnimatedContentTransitionScope.SlideDirection.End
    val duration = 400

    NavHost(navController = navController, startDestination = Routes.PROFILE) {
        composable(
            route = Routes.PROFILE,
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = towardsLeft,
                    animationSpec = tween(duration, easing = EaseIn)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = towardsRight,
                    animationSpec = tween(duration, easing = EaseIn)
                )
            }
        ) {
            ProfileScreen(navigateToGameScreen = { colorsCount ->
                navController.navigate("gameScreen/$colorsCount")
            }
            )
        }

        composable(
            route = Routes.GAME,
            arguments = listOf(navArgument("colorsCount") {
                type = NavType.IntType; defaultValue = 6
            }
            ),
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = towardsLeft,
                    animationSpec = tween(duration, easing = EaseIn)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = towardsRight,
                    animationSpec = tween(duration, easing = EaseIn)
                )
            }
        ) { backStackEntry ->
            val colorsCount = backStackEntry.arguments?.getInt("colorsCount") ?: 6
            GameScreen(
                colorsCount = colorsCount,
                navigateToProfileScreen = {
                    navController.navigate(Routes.PROFILE) {
                        popUpTo(Routes.PROFILE) { inclusive = true }
                    }
                },
                navigateToResultsScreen = { attemptCount: Int ->
                    navController.navigate("resultScreen/$attemptCount")
                }
            )
        }

        composable(
            route = Routes.RESULT,
            arguments = listOf(
                navArgument("score") { type = NavType.IntType; defaultValue = 0 }),
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = towardsLeft,
                    animationSpec = tween(duration, easing = EaseIn)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = towardsRight,
                    animationSpec = tween(duration, easing = EaseIn)
                )
            }
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score")
                ?: throw IllegalStateException("Score missing")
            ResultScreen(
                score,
                navigateToGameScreen = {
                    navController.popBackStack()
//                    val previousColorsCount =
//                        navController.previousBackStackEntry?.arguments?.getInt("colorsCount") ?: 6
//                    navController.navigate("gameScreen/$previousColorsCount") {
//                        popUpTo(Routes.RESULT) {
//                            inclusive = true
//                        }
//                    }
                },
                navigateToProfileScreen = {
                    navController.navigate(Routes.PROFILE) {
                        popUpTo(Routes.PROFILE) { inclusive = true }
                    }
                }
            )
        }
    }
}
