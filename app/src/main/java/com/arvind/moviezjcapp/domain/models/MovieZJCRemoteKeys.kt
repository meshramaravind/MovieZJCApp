package com.arvind.moviezjcapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arvind.moviezjcapp.utils.Constants.MOVIEZ_JC_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = MOVIEZ_JC_REMOTE_KEYS_DATABASE_TABLE)
data class MovieZJCRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
