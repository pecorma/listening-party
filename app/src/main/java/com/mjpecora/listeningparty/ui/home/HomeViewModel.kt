package com.mjpecora.listeningparty.ui.home

import androidx.lifecycle.viewModelScope
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.base.ViewState
import com.mjpecora.listeningparty.model.cache.User
import com.mjpecora.listeningparty.model.cache.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userDao: UserDao) : ViewModel() {

    val viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)

    init {
        viewModelScope.launch {
            userDao.getUser().collect {
                viewState.update(HomeViewState.Idle(it))
            }
        }
    }

    private fun <T : ViewState> MutableStateFlow<T>.update(value: T) = viewModelScope.launch {
        this@update.emit(value)
    }
}

sealed class HomeViewState : ViewState {
    data class Idle(val user: User) : HomeViewState()
    object Loading : HomeViewState()
}