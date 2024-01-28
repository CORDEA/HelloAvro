package jp.cordea.helloavro.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.helloavro.repositories.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            val users = repository.findAll()
            uiState = uiState.copy(
                items = users.asSequence().map {
                    HomeItemUiState(it.id, it.name)
                }.toList(),
                isLoading = false
            )
        }
    }

    var uiState by mutableStateOf(HomeUiState())
        private set

    fun onUserClicked(id: Int) {
        uiState = uiState.copy(requestUserId = id)
    }

    fun onUserDetailsShown() {
        uiState = uiState.copy(requestUserId = null)
    }
}

data class HomeItemUiState(val id: Int, val name: String)

data class HomeUiState(
    val items: List<HomeItemUiState> = emptyList(),
    val isLoading: Boolean = true,
    val requestUserId: Int? = null,
)
