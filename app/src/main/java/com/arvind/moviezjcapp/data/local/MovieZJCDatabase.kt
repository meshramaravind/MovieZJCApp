package com.arvind.moviezjcapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arvind.moviezjcapp.data.local.dao.MovieZJCRemoteKeyDao
import com.arvind.moviezjcapp.data.local.dao.MyListDao
import com.arvind.moviezjcapp.domain.models.MovieZJCRemoteKeys
import com.arvind.moviezjcapp.domain.models.MyList

@Database(
    entities = [MyList::class, MovieZJCRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MovieZJCDatabase: RoomDatabase() {
    abstract fun movieZJCRemoteKeyDao(): MovieZJCRemoteKeyDao
    abstract fun myListDao(): MyListDao
}