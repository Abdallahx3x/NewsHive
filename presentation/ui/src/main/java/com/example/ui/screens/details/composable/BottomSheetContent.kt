package com.example.ui.screens.details.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.example.ui.theme.customColors
import com.example.viewmodel.details.DetailsUiState


@Composable
fun BottomSheetContent(state: DetailsUiState) {
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .height(360.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "About",
            style = fontStyle.titleLarge,
            color = color.primary
        )
        Text(
            modifier = Modifier.padding(bottom = 32.dp, end = 24.dp),
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
                text = "Read more",
                style = fontStyle.titleLarge,
                color = color.onPrimary
            )
        }
    }
}