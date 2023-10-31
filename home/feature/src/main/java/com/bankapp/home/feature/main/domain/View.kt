package com.bankapp.home.feature.main.domain

import com.bankapp.home.model.domain.Movement

sealed interface View {
    object Home: View
    class MovementDetail(val movement: Movement): View
}
