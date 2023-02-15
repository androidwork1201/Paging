package com.example.pagingdemo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingdemo.model.Repo
import com.example.pagingdemo.network.GitHubServices

class RepoPagingSource(private val gitHubServices: GitHubServices) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val repoResponse = gitHubServices.searchRepo(page, pageSize)
            val repoItem = repoResponse.item
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItem.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItem, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int?  = null
}