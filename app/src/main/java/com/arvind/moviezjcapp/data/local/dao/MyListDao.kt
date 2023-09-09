package com.arvind.moviezjcapp.data.local.dao

import androidx.room.*
import com.arvind.moviezjcapp.domain.models.MyList
import kotlinx.coroutines.flow.Flow

@Dao
interface MyListDao {
    @Query("SELECT * FROM TABLE_NAME ORDER BY listId ASC")
    fun getMyList(): Flow<List<MyList>>

    @Query("SELECT * FROM TABLE_NAME  WHERE listId =:listId")
    fun getSelectedFromMyList(listId: Int): MyList

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToMyList(myList: MyList)

    @Query("SELECT EXISTS (SELECT 1 FROM TABLE_NAME WHERE listId = :listId)")
    suspend fun ifExists(listId: Int): Int

    @Query("DELETE FROM TABLE_NAME WHERE listId =:listId")
    suspend fun deleteOneFromMyList(listId: Int)

    @Query("DELETE FROM TABLE_NAME")
    suspend fun deleteAllContentFromMyList()
}