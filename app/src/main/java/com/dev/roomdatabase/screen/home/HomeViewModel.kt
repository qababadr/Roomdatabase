package com.dev.roomdatabase.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.roomdatabase.local.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {

    private val _dialogVisible: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val dialogVisible: StateFlow<Boolean>
        get() = _dialogVisible

    private val _productId: MutableStateFlow<Long> = MutableStateFlow(value = -1L)
    val productId: StateFlow<Long>
        get() = _productId

    val products = repository.getProducts()

    fun setProductId(productId: Long) {
        _productId.value = productId
    }

    fun showDialog() {
        _dialogVisible.value = true
    }


    fun hideDialog() {
        _dialogVisible.value = false
    }

    fun addToWishlist(onAddedToWishlist: (Long) -> Unit) {
        viewModelScope.launch {
            val addedToWishlistResult = repository.addToWishlist(
                productId = _productId.value,
                wishlistTitle = "My wishlist"
            )
            onAddedToWishlist(addedToWishlistResult)
        }
    }
}