package com.octopus.socialnetwork.data.repository

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import retrofit2.Response
import java.util.*

abstract class BaseRepository {


    protected suspend fun <I, O> wrap(
        function: suspend () -> Response<I>,
        mapper: (I) -> O
    ): O {
        val response = function()
        return if (response.isSuccessful) {
            response.body()?.let { mapper(it) } ?: throw Throwable()
        } else {
            throw Throwable("response is not successful")
        }
    }

    protected suspend fun <I, O> refreshWrapper(
        request: suspend () -> Response<BaseResponse<I>>,
        mapper: (I?) -> O?,
        insertIntoDatabase: suspend (O) -> Unit,
    ) {
        val response = request()
        if (response.isSuccessful) {
            val item = response.body()?.result
            mapper(item)?.let { it ->
                insertIntoDatabase(it)
            }
        } else {
            throw  Throwable()
        }
    }

    protected suspend fun refreshOneTimePerDay(
        requestDate: Long?,
        refreshData: suspend (Date) -> Unit
    ) {
        val currentDate = Date()
        if (requestDate != null) {
            if (Date(requestDate).after(currentDate)) {
                refreshData(currentDate)
            }
        } else {
            refreshData(currentDate)
        }
    }

}