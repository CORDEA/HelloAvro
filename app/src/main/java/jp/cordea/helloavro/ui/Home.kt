package jp.cordea.helloavro.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Home(viewModel: HomeViewModel = koinViewModel(), navController: NavController) {
    viewModel.uiState.requestUserId?.let {
        LaunchedEffect(it) {
            navController.navigate("details/${it}")
            viewModel.onUserDetailsShown()
        }
    }

    Scaffold(topBar = {
        MediumTopAppBar(
            title = { Text("Home") }
        )
    }) { padding ->
        if (viewModel.uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                viewModel.uiState.items.forEach {
                    item {
                        Item(viewModel = viewModel, uiState = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun Item(viewModel: HomeViewModel, uiState: HomeItemUiState) {
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .clickable { viewModel.onUserClicked(uiState.id) }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.CenterStart),
            text = uiState.name
        )
    }
}
