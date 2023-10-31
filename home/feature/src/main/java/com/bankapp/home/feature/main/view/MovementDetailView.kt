package com.bankapp.home.feature.main.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bankapp.home.feature.R
import com.bankapp.home.feature.main.domain.ToUI
import com.bankapp.home.feature.main.presentation.MainPresenter
import com.bankapp.home.model.domain.Movement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementDetailView(
    presenter: MainPresenter,
    movement: Movement,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = com.bankapp.components.R.string.components_top_app_bar_app_name)) },
                navigationIcon = {
                    IconButton(onClick = { presenter.dismissMovementDetailView() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(id = R.string.home_feature_cd_back_to_home),
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            BackHandler {
                presenter.dismissMovementDetailView()
            }
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = movement.movementName.string,
                        style = MaterialTheme.typography.headlineLarge,
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = movement.date.toString(),
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    movement.amount.ToUI()
                    Spacer(modifier = Modifier.height(20.dp))
                    if (movement.extraInformation.string.isNotBlank()) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .verticalScroll(rememberScrollState()),
                                text = movement.extraInformation.string,
                                minLines = 3,
                            )
                        }
                    }
                }
            }
        }
    }
}
