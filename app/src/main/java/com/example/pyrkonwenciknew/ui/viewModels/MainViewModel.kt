package com.example.pyrkonwenciknew.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pyrkonwenciknew.domain.model.GuestDomain
import com.example.pyrkonwenciknew.domain.usecase.GuestListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val guestListUseCase: GuestListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state = _state.asStateFlow()
    private var selectedGuest: GuestDomain? = null

    fun setSelectedGuest(guest: GuestDomain) {
        selectedGuest = guest
    }

    fun getSelectedGuest() = selectedGuest

    fun getGuestsList() = viewModelScope.launch {
        _state.emit(MainState.Loading)

        val response = guestListUseCase.getGuestsList()

        when {
            response.isSuccessful() && response.data != null -> {
                _state.emit(
                    MainState.ListLoaded(response.data)
                )
            }

            response.isError() -> {
                _state.emit(
                    MainState.Error(response.errorMessage)
                )
            }
        }
    }

    sealed class MainState {
        data object Loading : MainState()
        data class Error(
            val errorMessage: String?
        ) : MainState()

        data class ListLoaded(
            val guestsList: List<GuestDomain>
        ) : MainState()
    }
}