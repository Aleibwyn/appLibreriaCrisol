package pe.edu.crisol.libreria.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.crisol.libreria.retrofit.BookClient
import pe.edu.crisol.libreria.retrofit.request.SearchRequest
import pe.edu.crisol.libreria.retrofit.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchRepository {
    private val _searchResponse = MutableLiveData<SearchResponse>()
    val searchResponse get() = _searchResponse
    fun searchBooks(request: SearchRequest) : MutableLiveData<SearchResponse> {
        val call = BookClient
            .searchService
            .searchBooks(
                request.q,
                request.filter,
                request.langRestrict,
                request.maxResults,
                request.orderBy,
                request.printType,
                request.projection
            )
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                _searchResponse.value = response.body()
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
        return searchResponse
    }

    fun searchByCategory(category: String) : MutableLiveData<SearchResponse> {
        val call = BookClient
            .searchService
            .searchByCategory("subject:$category")
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                _searchResponse.value = response.body()
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
        return searchResponse
    }
}