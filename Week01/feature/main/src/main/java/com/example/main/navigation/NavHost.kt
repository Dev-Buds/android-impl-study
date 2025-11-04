package com.example.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookmark.ui.BookmarkScreen
import com.example.search.ui.SearchScreen

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Search,
    ) {
        composable<MainRoute.Search> {
            SearchScreen(
                navigateToBookmark = { navController.navigate(MainRoute.Bookmark) },
            )
        }

        composable<MainRoute.Bookmark> {
            BookmarkScreen(
                navigateToSearchScreen = { navController.navigate(MainRoute.Search) },
            )
        }
    }
}
