package com.example.ui.screens.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.ui.R
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    onChangeSearchQuery: (String) -> Unit,
    text: String,
) {
    val color = MaterialTheme.customColors
    val dimens=MaterialTheme.dimens
    val fontStyle = MaterialTheme.typography
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
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
                .padding(horizontal = dimens.space24, vertical = dimens.space16)
                .height(dimens.space56)
                .clip(RoundedCornerShape(dimens.space12)),
            value = text,
            textStyle= fontStyle.titleMedium,
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    color = color.onBackground60,
                    style = fontStyle.bodyMedium
                )
            },
            onValueChange = { onChangeSearchQuery(it) },
            shape = RoundedCornerShape(dimens.space12),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            )
        )
    }
}