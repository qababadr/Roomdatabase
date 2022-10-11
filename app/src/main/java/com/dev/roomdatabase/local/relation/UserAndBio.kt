package com.dev.roomdatabase.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.dev.roomdatabase.local.entity.BioEntity
import com.dev.roomdatabase.local.entity.UserEntity
import com.dev.roomdatabase.local.model.UserDetail

data class UserAndBio(

    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val bio: BioEntity

) {
    fun toUserDetail(): UserDetail{
        return UserDetail(
            uid = user.id,
            email = user.email,
            fullName = user.firstAndLastname,
            address = bio.address,
            phone = bio.phone
        )
    }
}
