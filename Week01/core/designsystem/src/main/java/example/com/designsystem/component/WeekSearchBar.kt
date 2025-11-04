package example.com.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekShapes
import example.com.designsystem.theme.WeekTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val searchBarState = rememberSearchBarState()

    SearchBar(
        state = searchBarState,
        inputField = {
            SearchTextFiled(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
            )
        },
        shape = WeekShapes.medium,
        modifier = modifier,
        colors =
            SearchBarDefaults.colors(
                containerColor = WeekColors.NeutralBackground,
                dividerColor = WeekColors.NeutralBackground,
            ),
    )
}

@Composable
private fun SearchTextFiled(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        colors =
            TextFieldDefaults.colors(
                cursorColor = WeekColors.NeutralOnBackground,
                unfocusedContainerColor = WeekColors.container,
                focusedContainerColor = WeekColors.container,
                focusedIndicatorColor = WeekColors.Primary.copy(alpha = 0.5f),
                unfocusedIndicatorColor = WeekColors.NeutralBackground,
            ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
    )
}

@Preview
@Composable
private fun WeekSearchBarPreview() {
    var query by remember { mutableStateOf("SearchBar") }

    WeekTheme {
        WeekSearchBar(
            query = query,
            onQueryChange = { new -> query = new },
            onSearch = {},
        )
    }
}
