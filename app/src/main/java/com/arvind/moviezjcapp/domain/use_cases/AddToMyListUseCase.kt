package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.data.repository.Repository
import com.arvind.moviezjcapp.domain.models.MyList

class AddToMyListUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(myList: MyList){
        return repository.addToMyList(myList =myList)
    }
}