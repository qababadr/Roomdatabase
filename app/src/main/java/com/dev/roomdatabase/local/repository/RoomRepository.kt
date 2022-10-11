package com.dev.roomdatabase.local.repository

import com.dev.roomdatabase.local.database.ProductDatabase
import com.dev.roomdatabase.local.entity.*
import com.dev.roomdatabase.local.model.Product
import com.dev.roomdatabase.local.model.UserDetail
import com.dev.roomdatabase.local.model.Wishlist
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.random.Random

class RoomRepository @Inject constructor(
    private val database: ProductDatabase
) {

    private val productDao = database.productDao()

    private val userDao = database.userDao()

    private val bioDao = database.bioDao()

    private val mediaDao = database.mediaDao()

    private val wishlistDao = database.wishlistDao()

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
        products.forEach {
            productDao.addProduct(
                product = ProductEntity(
                    productId = it.productId,
                    title = "Product ${it.productId}",
                    price = 49.99 + Random.nextInt(from = 50, until = 100),
                    description = "A brief description of the product ${it.productId}, enjoy our new arrival for this year."
                )
            )
            mediaDao.insertMedia(
                media = MediaEntity(
                    productId = it.productId,
                    url = it.imageUrl
                )
            )
        }
    }

    /**
     * Add a user to database
     *
     * @param email [String]
     * @param fullName [String]
     * @return [Long] The inserted user id otherwise the updated user id
     */
    suspend fun addOrUpdateUser(email: String, fullName: String): Long {
        return try {
            userDao.insertUser(user = UserEntity(email = email, firstAndLastname = fullName))
        } catch (exp: Exception) {
            -1L
        }
    }

    /**
     * add user bio to database
     *
     * @param userId [Long]
     * @param address [String]
     * @param phone [String]
     */
    suspend fun addOrUpdateBio(userId: Long, address: String, phone: String) {
        bioDao.insertBio(bio = BioEntity(userId = userId, address = address, phone = phone))
    }

    /**
     * Add a product to wishlist
     *
     * @param productId [Long]
     * @param wishlistTitle [String] optional
     * @return [Int] if -1 is returned, the insertion did not took place
     */
    suspend fun addToWishlist(productId: Long, wishlistTitle: String): Long {
        val user = userDao.checkUser().first() ?: return -1L
        val wishlistId = wishlistDao.addToWishlist(
            wish = WishlistEntity(
                productId = productId,
                userId = user.id,
                title = wishlistTitle
            )
        )
        wishlistDao.insertWishlistProductCrossRef(
            wishlistCrossRef = WishlistProductCrossRef(
                wishlistId = wishlistId,
                productId = productId
            )
        )
        return wishlistId
    }

    /**
     * Get products with medias
     *
     * @return [Flow]<[List]<[Product]>>
     */
    fun getProducts(): Flow<List<Product>> {
        return productDao.getProducts()
            .map { it.map { productWithMedias -> productWithMedias.toProduct() } }
    }


    /**
     * Get wishlist for a specific user
     *
     * @return [Flow]<[List]<[Wishlist]>>
     */
    fun getWishlists(): Flow<List<Wishlist>> {
        return flow {
            val user = userDao.checkUser().first()
            if (user == null) {
                emit(emptyList())
            } else {
                wishlistDao.getAllWishlistWithDetail(userId = user.id)
                    .collect {
                        emit(it.map { wishlistWithProductsAndMedias -> wishlistWithProductsAndMedias.toWishlist() })
                    }
            }
        }
    }

    /**
     *  Check if there is at least one user in the database, if we
     *
     *  find at least one user we call this user an active one the function return true, otherwise false
     *
     *  @return [Flow]<[Boolean]>
     */
    fun isActiveUser(): Flow<Boolean> {
        return flow {
            userDao.checkUser().collect { emit(value = it != null) }
        }
    }

    /**
     * Get a stream of last inserted user's detail
     *
     * @return [Flow]<[UserDetail]?>
     */
    fun getLastUser(): Flow<UserDetail?> {
        return userDao.getLastUser().map { it?.toUserDetail() }
    }

    /**
     * Delete a wishlist
     *
     * @param id [Long]
     */
    suspend fun deleteWishlist(id: Long) {
        wishlistDao.deleteWishlist(id = id)
    }

    /**
     * Clear necessary data
     */
    private suspend fun clear() {
        userDao.deleteAll()
        productDao.deleteAll()
        wishlistDao.deleteAll()
    }
}