package com.example.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.ui.theme.customColors
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.search.SearchInteraction
import com.example.viewmodel.search.SearchUiEffect
import com.example.viewmodel.search.SearchUiState
import com.example.viewmodel.search.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val color = MaterialTheme.customColors()
    val fontStyle=MaterialTheme.typography
    CollectUiEffect(viewModel.effect) { searchUiEffect ->
        when (searchUiEffect) {
            is SearchUiEffect.NavigateToDetails -> {
                navController.navigateToDetails(
                    searchUiEffect.newsItem
                )
            }
        }
    }
    AnimatedVisibility(visible = state.error != null) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.no_wifi)
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
            TextButton(onClick = { viewModel.getData() }) {
                androidx.compose.material.Text(
                    text = "Refresh", modifier = Modifier,
                    color = color.onBackground60,
                    style = fontStyle.titleMedium
                )
            }
        }

    }
    AnimatedVisibility(visible = state.error == null) {
        SearchContent(state, viewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    state: SearchUiState,
    searchInteraction: SearchInteraction
) {
    val color = MaterialTheme.customColors()
    val fontStyle=MaterialTheme.typography
    val query = state.query.collectAsState()
    NewsHiveScaffold(
        hasTitle = false,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color.card),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = color.background,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(12)),
                    value = query.value,
                    placeholder = { Text(text = "Search", color = color.onBackground60, style = fontStyle.titleSmall) },
                    onValueChange = {
                        searchInteraction.onChangeSearchQuery(it)
                    },
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null
                        )
                    }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp), contentPadding = PaddingValues(top = 16.dp)
            ) {
                items(state.searchItems) { card ->
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
                            onClick = {
                                searchInteraction.onClickSearchItem(card)
                            },
                            painter = rememberAsyncImagePainter(card.imageUrl),
                            category = card.category,
                            title = card.title,
                            date = card.publishedAt
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview()
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}