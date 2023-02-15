package com.example.pagingdemo.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagingdemo.network.GitHubServices
import com.example.pagingdemo.paging.RepoPagingSource
import kotlinx.coroutines.flow.Flow


/**使用Flow協程返回Flow<PagingData<Repo>>其中Repo可依據內容更改，其餘為公版*/
object Repository {

    private const val PAGE_SIZE = 50

    private val gitHubServices = GitHubServices.create()

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = {RepoPagingSource(gitHubServices)}
        ).flow
    }
}