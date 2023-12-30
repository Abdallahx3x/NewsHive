package com.example.ui.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.viewmodel.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CollectUiEffect(
    effect:SharedFlow<BaseViewModel.BaseUiEffect>,
    effectHandler:(effect:BaseViewModel.BaseUiEffect)->Unit
){
val throttleEffect=effect.throttleFirst(500)
    LaunchedEffect(Unit ){
        throttleEffect.collectLatest { effect->
            effectHandler(effect)
        }
    }
}


fun<T> Flow<T>.throttleFirst(periodMills:Long):Flow<T>{
    if(periodMills<0)return this
    return flow{
        conflate().collect { value->
            emit(value)
            delay(periodMills)
        }
    }
}