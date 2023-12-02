package com.example.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.details.composable.BlurredImageDetails
import com.example.ui.screens.details.composable.BottomSheetContent
import com.example.ui.theme.NewsHiveTheme
import com.example.ui.theme.customColors
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.details.DetailsInteraction
import com.example.viewmodel.details.DetailsUiEffect
import com.example.viewmodel.details.DetailsUiState
import com.example.viewmodel.details.DetailsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    CollectUiEffect(effect = viewModel.effect) { detailsUiEffect ->
        when (detailsUiEffect) {
            is DetailsUiEffect.NavigateBack -> navController.popBackStack()
        }
    }
    DetailsContent(state = state, detailsInteraction = viewModel)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    state: DetailsUiState,
    detailsInteraction: DetailsInteraction,
) {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.customColors()
    var offsetY by remember { mutableStateOf(0f) }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val screenHeight =
        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            BottomSheetContent(state = state)
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContainerColor = color.card,
        sheetPeekHeight = 0.dp
    ) {
        systemUiController.setSystemBarsColor(Color.Black, darkIcons = false)
        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    when {
                        dragAmount.y > 0 -> {
                            scope.launch {
                                bottomSheetScaffoldState.bottomSheetState.partialExpand()
                            }
                        }

                        dragAmount.y < 0 -> {
                            scope.launch {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        }
                    }
                    offsetY += dragAmount.y
                    if (offsetY > screenHeight) {
                        offsetY = screenHeight
                    }
                    if (offsetY < 0) {
                        offsetY = 0F
                    }
                }
            }) {

            BlurredImageDetails(state, detailsInteraction)
        }


    }
}

@Composable
@Preview
fun DetailsScreenPreview() {
    NewsHiveTheme {
        DetailsScreen(navController = rememberNavController())
    }
}

