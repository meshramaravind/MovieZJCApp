package com.arvind.moviezjcapp.domain.repository

import com.arvind.moviezjcapp.domain.models.MyList
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getMyList(): Flow<List<MyList>>

    fun getSelectedFromMyList(listId: Int):MyList

    suspend fun addToMyList(myList:MyList)

    suspend fun ifExists(listId:Int):Int

    suspend fun deleteOneFromMyList(myList: Int)

    suspend fun deleteAllContentFromMyList()

}