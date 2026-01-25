package com.ultraluv.arbiter.ui.navigation

sealed class Route(
    val route: String,
){
    data object IdleRoute: Route(
        route = "Idle"
    )
    data object HomeRoute: Route(
        route = "Home"
    )

    data object GameRoute: Route(
        route = "Game"
    )

    data object SettingRoute: Route(
        route = "Setting"
    )
}