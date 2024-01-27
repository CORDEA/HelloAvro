package jp.cordea.helloavro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.helloavro.repositories.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            val users = repository.findAll()
        }
    }
}
