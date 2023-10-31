package com.bankapp.home.feature.main.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.core.domain.Either
import com.bankapp.home.feature.main.domain.FetchingState
import com.bankapp.home.feature.main.domain.View
import com.bankapp.home.model.actions.FetchBankDetails
import com.bankapp.home.model.domain.BankDetails
import com.bankapp.home.model.domain.Movement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchBankDetails: FetchBankDetails,
    private val routeNavigator: RouteNavigator,
) : ViewModel(),
    MainPresenter,
    RouteNavigator by routeNavigator {
    private val _fetchingState = mutableStateOf<FetchingState>(FetchingState.Idle)
    override val fetchingState: State<FetchingState> = _fetchingState

    private val _view = mutableStateOf<View>(View.Home)
    override val view: State<View> = _view

    private val _bankDetails = mutableStateOf(BankDetails.Empty)
    override val bankDetails: State<BankDetails> = _bankDetails

    init {
        _fetchingState.value = FetchingState.Loading
        viewModelScope.launch {
            when (val result = fetchBankDetails.execute()) {
                is Either.Error -> _fetchingState.value = FetchingState.Error(result.error)
                is Either.Success -> {
                    _bankDetails.value = result.value
                    _fetchingState.value = FetchingState.Idle
                }
            }
        }
    }

    override fun dismissError() {
        _fetchingState.value = FetchingState.Idle
    }

    override fun movementClicked(movement: Movement) {
        _view.value = View.MovementDetail(movement)
    }

    override fun dismissMovementDetailView() {
        _view.value = View.Home
    }
}
