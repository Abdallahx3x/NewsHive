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
import com.example.ui.R
import com.example.ui.theme.NewsHiveTheme
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens
import com.example.viewmodel.details.DetailsUiState

@Composable
fun BottomSheetContent(state: DetailsUiState) {
    val color = MaterialTheme.customColors
    val fontStyle = MaterialTheme.typography
    val dimens = MaterialTheme.dimens
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimens.space380)
            .padding(horizontal = dimens.space16)
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = dimens.space16)
                .align(Alignment.TopCenter),
            text = stringResource(R.string.about),
            style = fontStyle.titleLarge,
            color = color.primary
        )
        Column(
            Modifier
                .padding(top = dimens.space32)
                .matchParentSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = dimens.space32, end = dimens.space24),
                text = state.content,
                style = fontStyle.titleMedium,
                color = color.onBackground87
            )
            Spacer(modifier = Modifier.weight(dimens.floatValues.float1))
            Button(
                onClick = { uriHandler.openUri(state.url) },
                modifier = Modifier
                    .padding(bottom = dimens.space16)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(color.primary),
                shape = RoundedCornerShape(dimens.space12)
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