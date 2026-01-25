package com.ultraluv.arbiter.ui.navigation

sealed class ScreenLists (
    val value: Int,
    val route: Route,
){
    data object Idle: ScreenLists(
        value = 0,
        route = Route.IdleRoute
    )
    data object Home: ScreenLists(
        value = 1,
        route = Route.HomeRoute
    )

    data object Game: ScreenLists(
        value = 2,
        route = Route.GameRoute
    )

    data object Setting: ScreenLists(
        value = 3,
        route = Route.SettingRoute
    )
}