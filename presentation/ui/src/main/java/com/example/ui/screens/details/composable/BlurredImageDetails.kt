package com.example.ui.screens.details.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.ActionSnakeBar
import com.example.ui.composable.GradientBackgroundBox
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens
import com.example.viewmodel.details.DetailsInteraction
import com.example.viewmodel.details.DetailsUiState

@Composable
fun BlurredImageDetails(
    state: DetailsUiState,
    detailsInteraction: DetailsInteraction,
) {
    val color = MaterialTheme.customColors
    val fontStyle = MaterialTheme.typography
    val dimens = MaterialTheme.dimens

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(dimens.space24),
            painter = painterResource(id = R.drawable.empty_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(dimens.space24),
            painter = rememberAsyncImagePainter(model = state.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        GradientBackgroundBox(
            gradientStartY = dimens.floatValues.float200,
            gradientEndY = dimens.floatValues.float0
        )
        Column(
            Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopImageContent(
                image = rememberAsyncImagePainter(model = state.imageUrl),
                onClickSaveIcon = { detailsInteraction.onClickSaveIcon(state) },
                onClickBackIcon = { detailsInteraction.onClickBackButton() },
                changeSaveIconColor = state.changeSavedIconColor,
            )
            Text(
                modifier = Modifier
                    .padding(vertical = dimens.space16, horizontal = dimens.space32),
                text = state.title,
                style = fontStyle.titleLarge,
                color = color.onPrimary
            )
            Spacer(modifier = Modifier.weight(dimens.floatValues.float1))
            SwipeToSeeMoreIndicator(color = color.onPrimary, fontStyle = fontStyle.titleSmall)
        }
        ActionSnakeBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            contentMessage = stringResource(R.string.item_saved_successfully),
            isVisible = state.changeSavedIconColor
        )
    }
}
