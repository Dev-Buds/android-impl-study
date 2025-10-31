package example.com.designsystem.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import example.com.designsystem.theme.WeekTheme
import example.com.designsystem.theme.spacing

@Preview(name = "WeekTheme 밝은 테마", showBackground = true)
@Composable
private fun WeekThemeLightPreview() {
    WeekTheme(useDarkTheme = false) {
        ThemePreviewContent()
    }
}

@Preview(name = "WeekTheme 어두운 테마", showBackground = true)
@Composable
private fun WeekThemeDarkPreview() {
    WeekTheme(useDarkTheme = true, useDynamicColor = false) {
        ThemePreviewContent()
    }
}

@Composable
private fun ThemePreviewContent(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        ) {
            Text(
                text = "제목",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "기본 본문 스타일을 보여주는 예시 텍스트입니다.",
                style = MaterialTheme.typography.bodyLarge,
            )
            Button(onClick = { }) {
                Text(text = "액션")
            }
        }
    }
}
