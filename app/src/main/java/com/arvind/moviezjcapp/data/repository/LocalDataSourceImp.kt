package com.arvind.moviezjcapp.data.repository

import com.arvind.moviezjcapp.data.local.MovieZJCDatabase
import com.arvind.moviezjcapp.domain.models.MyList
import com.arvind.moviezjcapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImp(
    movieZJCDatabase: MovieZJCDatabase
) : LocalDataSource {

    private val dao = movieZJCDatabase.myListDao()

    override fun getMyList(): Flow<List<MyList>> {
        return dao.getMyList()
    }

    override fun getSelectedFromMyList(listId: Int): MyList {
        return dao.getSelectedFromMyList(listId = listId)
    }

    override suspend fun addToMyList(myList: MyList) {
        return dao.addToMyList(myList = myList)
    }

    override suspend fun ifExists(listId: Int): Int {
        return dao.ifExists(listId)
    }

    override suspend fun deleteOneFromMyList(myList: Int) {
        return dao.deleteOneFromMyList(myList)
    }

    override suspend fun deleteAllContentFromMyList() {
        return dao.deleteAllContentFromMyList()
    }
}