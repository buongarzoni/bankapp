package com.bankapp.home.feature.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bankapp.components.modal.ErrorModal
import com.bankapp.components.modal.LoadingModal
import com.bankapp.components.screen.BankTopAppBar
import com.bankapp.home.feature.R
import com.bankapp.home.feature.main.domain.FetchingState
import com.bankapp.home.feature.main.domain.ToUI
import com.bankapp.home.feature.main.domain.View
import com.bankapp.home.feature.main.presentation.MainPresenter
import com.bankapp.home.model.domain.AccountBalance
import com.bankapp.home.model.domain.Amount
import com.bankapp.home.model.domain.Movement

@Composable
fun MainScreen(
    presenter: MainPresenter,
) = Scaffold(
    topBar = { BankTopAppBar() }
) {
    Content(Modifier.padding(it), presenter)
}

@Composable
private fun Content(
    modifier: Modifier,
    presenter: MainPresenter,
) = Box(modifier = modifier) {
    FetchingStateHandler(presenter)
    when (presenter.view.value) {
        View.Home -> HomeContent(presenter)
        is View.MovementDetail -> TODO()
    }
}

@Composable
private fun HomeContent(presenter: MainPresenter) {
    val bankDetails = presenter.bankDetails.value
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
    ) {
        CardAccountBalance(bankDetails.accountBalance)
        Spacer(modifier = Modifier.height(20.dp))
        HeadlineMovements()
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumnMovements(bankDetails.movements)
    }
}

@Composable
private fun CardAccountBalance(accountBalance: AccountBalance) = Card(
    modifier = Modifier.fillMaxWidth()
) {
    Row(
        horizontalArrangement = Arrangement.Center,
    ) {
        val modifier = Modifier
            .weight(1f)
            .padding(vertical = 8.dp)
        BalanceForAmount(
            modifier = modifier,
            amount = accountBalance.income,
            label = stringResource(id = R.string.home_feature_label_income),
        )
        BalanceForAmount(
            modifier = modifier,
            amount = accountBalance.expense,
            label = stringResource(id = R.string.home_feature_label_expense),
        )
    }
}

@Composable
private fun BalanceForAmount(
    modifier: Modifier,
    amount: Amount,
    label: String,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = label,
        style = MaterialTheme.typography.labelLarge,
    )
    amount.ToUI()
}

@Composable
private fun HeadlineMovements() = Text(
    text = stringResource(id = R.string.home_feature_headline_movements),
    style = MaterialTheme.typography.headlineSmall,
)

@Composable
private fun LazyColumnMovements(
    movements: List<Movement>,
) = LazyColumn(
    modifier = Modifier
        .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    items(movements) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = it.movementName.string,
                    fontWeight = FontWeight.Bold,
                )
                it.amount.ToUI()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun FetchingStateHandler(presenter: MainPresenter) {
    when (val state = presenter.fetchingState.value) {
        is FetchingState.Error -> ErrorModal(
            onDismiss = { presenter.dismissError() },
            onConfirm = { presenter.dismissError() },
            text = stringResource(id = state.resId),
        )

        FetchingState.Idle -> Unit
        FetchingState.Loading -> LoadingModal(
            title = stringResource(id = R.string.home_feature_headline_loading_bank_details),
        ) { /* can not dismiss */ }
    }
}
