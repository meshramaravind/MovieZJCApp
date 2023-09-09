package com.arvind.moviezjcapp.data.local.dao

import androidx.room.*
import com.arvind.moviezjcapp.domain.models.MovieZJCRemoteKeys

@Dao
interface MovieZJCRemoteKeyDao {

    @Query("SELECT * FROM MOVIEZ_JC_REMOTE_KEYS_TABLE WHERE id=:remoteKey")
    suspend fun getMovieZJCRemoteKeys(remoteKey: Int): MovieZJCRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(movieZJCRemoteKeys: List<MovieZJCRemoteKeys>)

    @Query("DELETE FROM MOVIEZ_JC_REMOTE_KEYS_TABLE")
    suspend fun deleteAllRemoteKeys()
}