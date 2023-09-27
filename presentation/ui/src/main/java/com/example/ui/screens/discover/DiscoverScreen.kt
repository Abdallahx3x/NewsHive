package com.example.ui.screens.discover

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ui.screens.discover.composable.DiscoverCard
import com.example.ui.theme.customColors
import com.example.viewmodel.discover.DiscoverInteraction
import com.example.viewmodel.discover.DiscoverUiState
import com.example.viewmodel.discover.DiscoverViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    DiscoverContent(state, viewModel)
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DiscoverContent(
    state: DiscoverUiState,
    discoverInteraction: DiscoverInteraction
) {
    val pageState = rememberPagerState()
    val pages = listOf("sports", "technology", "science", "health", "business", "entertainment")
    val color = MaterialTheme.customColors()
    val tabIndicator = @Composable { tabPosition: List<TabPosition> ->
        TabIndicator(pageState, tabPosition)
    }
    LaunchedEffect(state.pageIndex) {
        pageState.animateScrollToPage(state.pageIndex!!)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color.background)
    ) {
        ScrollableTabRow(modifier = Modifier.height(86.dp),
            selectedTabIndex = pageState.currentPage,
            indicator = tabIndicator,
            backgroundColor = color.card,
            divider = { Divider(thickness = 0.dp, color = color.background) }) {
            pages.forEachIndexed { index, text ->
                Tab(modifier = Modifier
                    .zIndex(6f)
                    .clip(RoundedCornerShape(50)),
                    selected = pageState.currentPage == index,
                    onClick = { discoverInteraction.updatePageIndex(index) },
                    text = {
                        Text(
                            modifier = Modifier,
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (pageState.currentPage == index) color.card else color.onBackground87
                        )
                    }
                )
            }
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            count = pages.size,
            state = pageState
        ) { page ->
            val newList = when (page) {
                0 -> state.sportsNews
                1 -> state.technologyNews
                2 -> state.scienceNews
                3 -> state.healthNews
                4 -> state.businessNews
                else -> null
            }

            newList?.let { news ->

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(news) { item ->
                        DiscoverCard(
                            painter = rememberAsyncImagePainter(model = item.imageUrl),
                            category = item.category,
                            title = item.title,
                            date = item.publishedAt,
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabIndicator(pagerState: PagerState, tabPositions: List<TabPosition>) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)

            }
        }, label = ""
    ) {
        tabPositions[it].left
    }


    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)

            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = MaterialTheme.customColors().primary, RoundedCornerShape(50))
            //.border(BorderStroke(2.dp, MaterialTheme.customColors().card), RoundedCornerShape(50))
            .zIndex(1f)
    )
}

@Composable
@Preview
fun DiscoverScreenPreview() {
    DiscoverScreen()
}