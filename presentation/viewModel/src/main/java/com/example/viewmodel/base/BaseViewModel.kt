package com.example.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.entities.NetworkException
import com.example.entities.NewsHiveException
import com.example.entities.NullDataException
import com.example.entities.RateLimitExceededException
import com.example.entities.UnAuthorizedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, UiEffect>(initState: STATE) : ViewModel() {
    interface BaseUiEffect
    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initState) }
    val state = _state.asStateFlow()

    protected val _effect = MutableSharedFlow<UiEffect>()
    val effect = _effect.asSharedFlow()

    fun <T> tryToExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (e: NetworkException) {
                onError(e)
            } catch (e: UnAuthorizedException) {
                onError(e)
            } catch (e: NullDataException) {
                onError(e)
            } catch (e: RateLimitExceededException) {
                onError(e)
            } catch (e: NewsHiveException) {
                onError(e)
            }
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        updateState: STATE.(T) -> STATE
    ) {
        viewModelScope.launch {
            flow.collect { value ->
                _state.update { it.updateState(value) }
            }
        }
    }

    fun <T : Any> tryToExecutePaging(
        call: suspend () -> Flow<PagingData<T>>,
        onSuccess: suspend (PagingData<T>) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = call().cachedIn(viewModelScope)
                result.collect { data ->
                    onSuccess(data)
                }
            } catch (e: NetworkException) {
                onError(e)
            } catch (e: UnAuthorizedException) {
                onError(e)
            } catch (e: NullDataException) {
                onError(e)
            } catch (e: RateLimitExceededException) {
                onError(e)
            } catch (e: NewsHiveException) {
                onError(e)
            }
        }
    }

    protected fun sendUiEffect(effect: UiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }

}