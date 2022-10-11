package com.dev.roomdatabase.screen.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.roomdatabase.local.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: RoomRepository,
):ViewModel(){

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _fullName: MutableStateFlow<String> = MutableStateFlow(value = "")
    val fullName: StateFlow<String>
        get() = _fullName

    private val _email: MutableStateFlow<String> = MutableStateFlow(value = "")
    val email: StateFlow<String>
        get() = _email

    private val _address: MutableStateFlow<String> = MutableStateFlow(value = "")
    val address: StateFlow<String>
        get() = _address

    private val _phone: MutableStateFlow<String> = MutableStateFlow(value = "")
    val phone: StateFlow<String>
        get() = _phone

    private val _isValidForm: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    init {
        viewModelScope.launch {
            val lastUser = repository.getLastUser()
            _fullName.value = lastUser.first()?.fullName ?:""
            _email.value = lastUser.first()?.email ?:""
            _address.value = lastUser.first()?.address ?:""
            _phone.value = lastUser.first()?.phone ?:""
        }
    }

    fun addOrUpdateUser(onDeleted: () -> Unit) {
        isValidForm()
        if (_isValidForm.value) {
            viewModelScope.launch {
                _isLoading.value = true
                val insertedUId =
                    async {
                        repository.addOrUpdateUser(
                            fullName = _fullName.value,
                            email = _email.value
                        )
                    }
                val userId = insertedUId.await()
                repository.addOrUpdateBio(
                    userId = if(userId == -1L) 1L else userId,
                    address = _address.value,
                    phone = _phone.value
                )
                onDeleted()
                _isLoading.value = false
            }
        }
    }

    fun onFullNameChanged(fullName: String){
        _fullName.value = fullName
    }


    fun onEmailChanged(email: String){
        _email.value = email
    }

    fun onAddressChanged(address: String){
        _address.value = address
    }

    fun onPhoneChanged(phone: String){
        _phone.value = phone
    }

    private fun isValidForm() {
        _isValidForm.value =
            _fullName.value.length > 3 && _email.value.length > 5 && _address.value.length > 5 && _phone.value.length > 8
    }

}