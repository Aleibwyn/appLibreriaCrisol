package pe.edu.crisol.libreria.viewModel

import android.telecom.Call.Details
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.repository.DetailsRepository
import pe.edu.crisol.libreria.retrofit.BookClient
import pe.edu.crisol.libreria.retrofit.request.DetailsRequest
import pe.edu.crisol.libreria.retrofit.response.DetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishListViewModel: ViewModel() {

    private val _booksLiveData = MutableLiveData<List<Book>>()
    val booksLiveData: LiveData<List<Book>> = _booksLiveData

    fun fetchBooksByIds(bookIds: List<String>) {
        val books = mutableListOf<Book>()

        for (bookId in bookIds) {
            BookClient.bookService.searchBookById(bookId).enqueue(object :
                Callback<DetailsResponse> {
                override fun onResponse(call: Call<DetailsResponse>, response: Response<DetailsResponse>) {
                    if (response.isSuccessful) {
                        val bookDetailsResponse = response.body()
                        if (bookDetailsResponse != null) {
                            // Aquí puedes mapear los datos de la respuesta a tu modelo de datos Book
                            // Por ejemplo, bookDetailsResponse.title, bookDetailsResponse.author, etc.
                            val book = Book(
                                bookDetailsResponse.id,
                                bookDetailsResponse.id,
                                bookDetailsResponse.etag,
                                bookDetailsResponse.selfLink,
                                bookDetailsResponse.volumeInfo,
                                bookDetailsResponse.saleInfo,
                                bookDetailsResponse.accessInfo,
                                null

                            )
                            books.add(book)
                            _booksLiveData.value = books
                        }
                    } else {
                        // Manejar respuesta de error
                    }
                }

                override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {
                    // Manejar falla de la solicitud
                }
            })
        }
    }

}