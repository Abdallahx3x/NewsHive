package com.example.ui.screens.favourites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ui.R
import com.example.ui.composable.NewsHiveCard
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.favourites.composable.DismissBackground
import com.example.ui.theme.customColors
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.favourites.FavouritesInteraction
import com.example.viewmodel.favourites.FavouritesUiEffect
import com.example.viewmodel.favourites.FavouritesUiState
import com.example.viewmodel.favourites.FavouritesViewModel


@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    CollectUiEffect(viewModel.effect) { favouritesUiEffect ->
        when (favouritesUiEffect) {
            is FavouritesUiEffect.NavigateToDetails -> {
                navController.navigateToDetails(
                    favouritesUiEffect.newsItem
                )
            }
        }
    }

    FavouritesContent(state, viewModel)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesContent(
    state: FavouritesUiState,
    favouritesInteraction: FavouritesInteraction
) {
    val color = MaterialTheme.customColors()
    NewsHiveScaffold(
        hasTitle = true,
        title = "Favourites"
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color.background)
        ) {
            AnimatedVisibility(state.favouritesItemUiState.isEmpty()) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.empty)
                )
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = LottieConstants.IterateForever,
                    restartOnPlay = true
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress })
                }
            }


            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(state.favouritesItemUiState, key = { it.title }) { card ->
                    val dismissState = rememberDismissState(
                        confirmValueChange = {
                            when (it) {
                                DismissValue.Default -> false
                                DismissValue.DismissedToStart -> false
                                DismissValue.DismissedToEnd -> {
                                    favouritesInteraction.onDismissNews(card.title)

                                    true
                                }
                            }
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            DismissBackground()
                        }, dismissContent = {
                            Box {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .padding(
                                            bottom = 16.dp,
                                            start = 26.dp
                                        )
                                        .size(24.dp)
                                        .align(Alignment.CenterStart)
                                )

                                NewsHiveCard(
                                    modifier = Modifier.padding(bottom = 16.dp),
                                    onClick = { favouritesInteraction.onClickFavouriteItem(card) },
                                    painter = rememberAsyncImagePainter(card.imageUrl),
                                    category = card.category,
                                    title = card.title,
                                    date = card.publishedAt
                                )

                            }
                        }, directions = setOf(DismissDirection.StartToEnd)
                    )


                }
            }


        }

    }

}

@Composable
@Preview()
fun HomeScreenPreview() {
    FavouritesScreen(navController = rememberNavController())
}
