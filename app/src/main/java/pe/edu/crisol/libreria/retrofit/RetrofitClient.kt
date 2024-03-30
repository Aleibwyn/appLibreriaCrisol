package pe.edu.crisol.libreria.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.18.1:8080/WS_REST_Login/webresources/"

    // Crear una instancia de Retrofit
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Obtener una instancia del servicio API
    val apiService: LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java)
    }
}