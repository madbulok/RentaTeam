package com.uzlov.rentateam.repo.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.uzlov.rentateam.repo.room.Converters
import java.util.*

@Entity
@TypeConverters(Converters::class)
data class UserModel(
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    val data: List<Data>?,
    val page: Int?,
    val per_page: Int?,
    val support: Support?,
    val total: Int?,
    val total_pages: Int?
)