package com.dev.roomdatabase.screen.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.roomdatabase.local.model.Wishlist
import com.dev.roomdatabase.local.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: RoomRepository,
) : ViewModel() {

    val wishlist: Flow<List<Wishlist>> = flow {
        repository.getWishlists()
            .flowOn(Dispatchers.IO)
            .collect {
                emit(it)
            }
    }

    fun deleteWishlist(id: Long) {
        viewModelScope.launch {
            repository.deleteWishlist(id = id)
        }
    }

}