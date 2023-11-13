package com.example.ui.screens.favourites.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.customColors

@Composable
fun DismissBackground() {
    val colors = MaterialTheme.customColors()

    Row(
        modifier = Modifier
            .height(90.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.red)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.trashicon),
            tint = MaterialTheme.customColors().card,
            contentDescription = ""
        )
    }

}