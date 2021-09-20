package com.uzlov.rentateam.repo.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.uzlov.rentateam.repo.room.Converters
import kotlinx.parcelize.Parcelize

@Entity
@TypeConverters(Converters::class)
@Parcelize
data class Data(
    val avatar: String?,
    val email: String?,
    val first_name: String?,
    @PrimaryKey val id: Int?,
    val last_name: String?
) : Parcelable