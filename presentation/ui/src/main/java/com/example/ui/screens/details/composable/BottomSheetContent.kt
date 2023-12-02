package com.example.ui.screens.details.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.NewsHiveTheme
import com.example.ui.theme.customColors
import com.example.viewmodel.details.DetailsUiState

@Composable
fun BottomSheetContent(state: DetailsUiState) {
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.TopCenter),
            text = stringResource(R.string.about),
            style = fontStyle.titleLarge,
            color = color.primary
        )
        Column(
            Modifier
                .padding(top = 33.dp)
                .matchParentSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 32.dp, end = 24.dp),
                text = state.content,
                style = fontStyle.titleMedium,
                color = color.onBackground87
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { uriHandler.openUri(state.url) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(color.primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.read_more),
                    style = fontStyle.titleLarge,
                    color = color.onPrimary
                )
            }
        }
    }
}

@Composable
@Preview
fun BottomSheetContentPreview() {
    NewsHiveTheme {
        BottomSheetContent(
            state = DetailsUiState(
                content = "Hello Content Hello Content" +
                          "Hello Content Hello Content"
            )
        )
    }
}