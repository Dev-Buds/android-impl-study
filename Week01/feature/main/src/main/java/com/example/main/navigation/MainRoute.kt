package com.example.main.navigation

import kotlinx.serialization.Serializable

sealed interface MainRoute {
    @Serializable
    data object Search : MainRoute

    @Serializable
    data object Bookmark : MainRoute
}
