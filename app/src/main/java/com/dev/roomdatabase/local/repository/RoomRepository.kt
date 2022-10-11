package com.dev.roomdatabase.local.repository

import com.dev.roomdatabase.local.model.Product
import com.dev.roomdatabase.local.model.UserDetail
import com.dev.roomdatabase.local.model.Wishlist
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RoomRepository @Inject constructor(

) {


    companion object {

        data class Image(
            val productId: Long,
            val imageUrl: String,
        )

        val products = listOf(
            Image(
                productId = 845434,
                imageUrl = "https://images.pexels.com/photos/845434/pexels-photo-845434.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 701877,
                imageUrl = "https://images.pexels.com/photos/701877/pexels-photo-701877.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 837140,
                imageUrl = "https://images.pexels.com/photos/837140/pexels-photo-837140.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 934063,
                imageUrl = "https://images.pexels.com/photos/934063/pexels-photo-934063.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 206434,
                imageUrl = "https://images.pexels.com/photos/206434/pexels-photo-206434.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 1375736,
                imageUrl = "https://images.pexels.com/photos/1375736/pexels-photo-1375736.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 1043474,
                imageUrl = "https://images.pexels.com/photos/1043474/pexels-photo-1043474.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 3620411,
                imageUrl = "https://images.pexels.com/photos/3620411/pexels-photo-3620411.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 2529148,
                imageUrl = "https://images.pexels.com/photos/2529148/pexels-photo-2529148.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
            Image(
                productId = 1858488,
                imageUrl = "https://images.pexels.com/photos/1858488/pexels-photo-1858488.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            ),
        )
    }

    /**
     *  Seed database with some initial data
     */
    suspend fun seed() {
        clear()

    }

    /**
     * Add a user to database
     *
     * @param email [String]
     * @param fullName [String]
     * @return [Long] The inserted user id otherwise the updated user id
     */
    suspend fun addOrUpdateUser(email: String, fullName: String): Long {
        return -1L
    }

    /**
     * add user bio to database
     *
     * @param userId [Long]
     * @param address [String]
     * @param phone [String]
     */
    suspend fun addOrUpdateBio(userId: Long, address: String, phone: String) {

    }

    /**
     * Add a product to wishlist
     *
     * @param productId [Long]
     * @param wishlistTitle [String] optional
     * @return [Int] if -1 is returned, the insertion did not took place
     */
    suspend fun addToWishlist(productId: Long, wishlistTitle: String): Long {
        return -1
    }

    /**
     * Get products with medias
     *
     * @return [Flow]<[List]<[Product]>>
     */
    fun getProducts(): Flow<List<Product>> {
        return emptyFlow()
    }


    /**
     * Get wishlist for a specific user
     *
     * @return [Flow]<[List]<[Wishlist]>>
     */
    fun getWishlists(): Flow<List<Wishlist>> {
        return emptyFlow()
    }

    /**
     *  Check if there is at least one user in the database, if we
     *
     *  find at least one user we call this user an active one the function return true, otherwise false
     *
     *  @return [Flow]<[Boolean]>
     */
    fun isActiveUser(): Flow<Boolean> {
        return emptyFlow()
    }

    /**
     * Get a stream of last inserted user's detail
     *
     * @return [Flow]<[UserDetail]?>
     */
    fun getLastUser(): Flow<UserDetail?> {
        return emptyFlow()
    }

    /**
     * Delete a wishlist
     *
     * @param id [Long]
     */
    suspend fun deleteWishlist(id: Long){

    }

    /**
     * Clear user and bio
     */
    private suspend fun clear() {

    }
}