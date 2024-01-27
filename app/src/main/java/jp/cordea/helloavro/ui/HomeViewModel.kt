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
            items = users.asSequence().map {
                HomeItemViewModel(it.id, it.name)
            }.toList()
        }
    }

    var items by mutableStateOf<List<HomeItemViewModel>>(emptyList())
        private set
}

data class HomeItemViewModel(val id: Int, val name: String)
