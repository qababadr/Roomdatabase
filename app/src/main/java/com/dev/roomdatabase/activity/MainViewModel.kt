package com.dev.roomdatabase.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.roomdatabase.local.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {

    val isActiveUser: Flow<Boolean> = repository.isActiveUser()

    private val _isSeeding: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isSeeding: StateFlow<Boolean>
         get() = _isSeeding

    init {
        viewModelScope.launch {
            _isSeeding.value = true
//            repository.seed()
            delay(3000)
            _isSeeding.value = false
        }
    }

}