package pe.edu.crisol.libreria.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BookClient {
    private var retrofitClient = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        .build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .client(retrofitClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bookService: BookService by lazy {
        buildRetrofit().create(BookService::class.java)
    }
}