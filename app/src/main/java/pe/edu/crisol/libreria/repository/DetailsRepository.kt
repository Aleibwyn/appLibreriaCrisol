package pe.edu.crisol.libreria.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.edu.crisol.libreria.retrofit.BookClient
import pe.edu.crisol.libreria.retrofit.request.DetailsRequest
import pe.edu.crisol.libreria.retrofit.response.DetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepository {

    val detailsResponse = MutableLiveData<DetailsResponse>()

    fun searchBookById(detailsRequest: DetailsRequest): MutableLiveData<DetailsResponse> {
        val call = BookClient.bookService.searchBookById(detailsRequest.id)
        call.enqueue(object : Callback<DetailsResponse> {
            override fun onResponse(p0: Call<DetailsResponse>, p1: Response<DetailsResponse>) {
                detailsResponse.value = p1.body()
            }

            override fun onFailure(p0: Call<DetailsResponse>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return detailsResponse
    }





}