package com.arvind.moviezjcapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arvind.moviezjcapp.utils.Constants.TABLE_NAME

@Entity(tableName =TABLE_NAME)
data class MyList(
    @PrimaryKey
    val listId: Int,
    val imagePath: String?,
    val title: String,
    val description: String,
    val mediaType: String
)

