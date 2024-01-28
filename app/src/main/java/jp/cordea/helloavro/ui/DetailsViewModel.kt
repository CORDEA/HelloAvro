package jp.cordea.helloavro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.helloavro.repositories.UserRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: UserRepository, private val id: Int) : ViewModel() {
    init {
        viewModelScope.launch {
            val details = repository.find(id)
        }
    }
}
