package com.example.search.ui

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
fun SearchScreen(navigateToBookmark: () -> Unit) {
    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(it).background(Color.Blue),
        ) {
            TextButton(
                onClick = navigateToBookmark,
            ) {
                Text(text = "navigate to bookmark screen")
            }
        }
    }
}
