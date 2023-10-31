package com.bankapp.home.feature.main.presentation

import androidx.compose.runtime.State
import com.bankapp.home.feature.main.domain.FetchingState
import com.bankapp.home.feature.main.domain.View
import com.bankapp.home.model.domain.BankDetails
import com.bankapp.home.model.domain.Movement

interface MainPresenter {
    val fetchingState: State<FetchingState>
    val view: State<View>
    val bankDetails: State<BankDetails>

    fun dismissError()
    fun movementClicked(movement: Movement)
    fun dismissMovementDetailView()
}
