package com.accubits.movie.data.network

import com.accubits.movie.data.network.response.MovieResponse
import com.accubits.movie.utils.APIException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val aResponse = call.invoke()
        if (aResponse.isSuccessful) {
            return aResponse.body()!!
        } else {
            val error = aResponse.errorBody()?.string()
            val message = StringBuilder()

            error?.let {
                try {
                    message.append(JSONObject(it).getString("error"))
                } catch (e: JSONException) {
                    message.append("\n")
                }
            }

            message.append(" Error code : ${aResponse.code()} ")
            throw APIException(message.toString())
        }
    }
}