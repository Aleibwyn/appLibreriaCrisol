package pe.edu.crisol.libreria.viewModel

/*import android.util.Log
import androidx.lifecycle.LiveData*/
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.repository.SearchRepository
import pe.edu.crisol.libreria.retrofit.request.SearchRequest
import pe.edu.crisol.libreria.retrofit.response.SearchResponse

class SearchViewModel : ViewModel() {
    private val repository = SearchRepository()

    private var _searchResponse: MutableLiveData<SearchResponse> = repository.searchResponse
    val searchResponse: LiveData<SearchResponse> get() = _searchResponse

    val bookId = MutableLiveData<String>("")
    fun sendBookId(newBookId: String) {
        bookId.value = newBookId
    }
    fun searchBooks(q: String) {
        _searchResponse = repository.searchBooks(
            SearchRequest(
                q,
                "paid-ebooks",
                "",
                10,
                "relevance",
                "books",
                "full"
            )
        )
    }

/*    fun searchBooksByCategory(q: String) {
        _searchResponse = repository.searchBooks(
            SearchRequest(
                q,
                "paid-ebooks",
                "",
                10,
                "relevance",
                "books",
                "full"
            )
        )
    }*/

}
