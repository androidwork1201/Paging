package com.example.pagingdemo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingdemo.model.Repo
import com.example.pagingdemo.network.GitHubServices

/**PagingSource 聲明兩個成員(1:頁數數據類型，無特殊需求使用Int即可，2:每項數據的格式)*/
class RepoPagingSource(private val gitHubServices: GitHubServices) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            /**標示當前頁數如果為Null將當前頁數設定為第一頁*/
            val page = params.key ?: 1
            /**表示每頁有多少筆*/
            val pageSize = params.loadSize
            /**調用在GitHubServices內的searchRepo方法將page, pageSize傳入*/
            val repoResponse = gitHubServices.searchRepo(page, pageSize)
            /***/
            val repoItem = repoResponse.item
            /**取前一頁內容*/
            val prevKey = if (page > 1) page - 1 else null
            /**取後一頁內容*/
            val nextKey = if (repoItem.isNotEmpty()) page + 1 else null
            /**返回Page,Page內傳入(1:Repo列表，2:前一頁，3:後一頁)*/
            LoadResult.Page(repoItem, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int?  = null
}