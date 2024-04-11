package pe.edu.crisol.libreria.retrofit

import pe.edu.crisol.libreria.retrofit.response.DetailsResponse
import pe.edu.crisol.libreria.retrofit.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    fun searchBooks(
        @Query("q") q: String,
        @Query("filter") filter: String,
        @Query("langRestrict") langRestrict: String,
        @Query("maxResults") maxResults: Int,
        @Query("orderBy") orderBy: String,
        @Query("printType") printType: String,
        @Query("projection") projection: String) : Call<SearchResponse>

    @GET("volumes/{bookId}")
    fun searchBookById(
        @Path("bookId") bookId: String
    ) : Call<DetailsResponse>
}