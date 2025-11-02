package com.example.bookmark.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BookmarkScreen(navigateToSearchScreen: () -> Unit) {
    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(it).background(Color.Red),
        ) {
            TextButton(
                onClick = navigateToSearchScreen,
            ) {
                Text(text = "navigate to search screen")
            }
        }
    }
}
