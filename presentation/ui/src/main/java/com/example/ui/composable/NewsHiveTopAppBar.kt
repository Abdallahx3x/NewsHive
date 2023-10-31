package com.example.ui.composable

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.ui.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHiveTopAppBar(
    title: String
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.customColors().card,
            titleContentColor = MaterialTheme.customColors().primary,
        ),
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}