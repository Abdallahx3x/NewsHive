package com.example.remote.util

import com.example.entities.NetworkException
import com.example.entities.NewsHiveException
import com.example.entities.NoInternetException
import com.example.entities.NullDataException
import com.example.entities.UnAuthorizedException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> wrapApiCall(request:suspend ()->Response<T>):T{
    return try {
        val result=request()
        when(result.code()){
            HttpStatusCodes.UNAUTHORIZED.code->throw UnAuthorizedException(result.message())
            HttpStatusCodes.NOT_FOUND.code->throw NullDataException(result.message())
            else -> result.body()?:throw NullDataException(result.message())
        }
    }catch(e:UnknownHostException) {
        throw NoInternetException(e.message)
    }catch (e:IOException){
        throw NetworkException(e.message)
    }catch (e:Exception){
        throw NewsHiveException(e.message)
    }
}