package com.example.week01.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.week01.data.source.remote.SearchDataSource
import com.example.week01.domain.model.Media
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val searchDataSource: SearchDataSource,
    private val query: String,
) : PagingSource<Int, Media>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> =
        try {
            val page = params.key ?: 1
            val size = params.loadSize

            val imageResponseDeferred =
                coroutineScope {
                    async {
                        searchDataSource.fetchImage(
                            query = query,
                            page = page,
                            size = size,
                        )
                    }
                }
            val videoResponseDeferred =
                coroutineScope {
                    async {
                        searchDataSource.fetchVideo(
                            query = query,
                            page = page,
                            size = size,
                        )
                    }
                }

            val imageResponse = imageResponseDeferred.await()
            val videoResponse = videoResponseDeferred.await()

            val mediaItems = imageResponse.toDomain() + videoResponse.toDomain()
            val sortedMedia = mediaItems.sortedByDescending { it.createdAt }

            val nextKey = if (mediaItems.isEmpty()) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            LoadResult.Page(
                data = sortedMedia,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}
