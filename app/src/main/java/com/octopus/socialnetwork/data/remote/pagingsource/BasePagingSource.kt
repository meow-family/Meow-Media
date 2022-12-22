package com.octopus.socialnetwork.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource<V: Any> : PagingSource<Int, V>() {
    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    abstract suspend fun getData(page: Int): List<V>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val page = params.key ?: 1
        val prevKey = if (page == 1) null else page - 1

        return try {
            val pageItems = getData(page)
            val nextKey = if (pageItems.isEmpty() || page == MAX_PAGE_NUMBER) { null } else {
                page + 1
            }
            val itemsBefore = (page - 1) * ITEMS_PER_PAGE
            val itemsAfter = if (pageItems.isEmpty() || page == MAX_PAGE_NUMBER) { 0 } else {
                ITEMS_PER_PAGE
            }
            LoadResult.Page(
                data = pageItems,
                prevKey = prevKey,
                nextKey = nextKey,
                itemsBefore = itemsBefore,
                itemsAfter = itemsAfter,
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        private const val ITEMS_PER_PAGE = 10
        private const val MAX_PAGE_NUMBER = 500
    }
}