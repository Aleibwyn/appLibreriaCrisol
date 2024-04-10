package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.repository.SearchRepository
import pe.edu.crisol.libreria.retrofit.request.SearchRequest
import pe.edu.crisol.libreria.retrofit.response.SearchResponse

class HomeViewModel : ViewModel() {
    /*private val repository = SearchRepository()*/
/*    val bookId = MutableLiveData<String>("")*/

    fun loadCategory(category: String): LiveData<SearchResponse> {
        return SearchRepository().searchBooks(
            SearchRequest(
                "subject:${category}",
                "ebooks",
                "",
                20,
                "relevance",
                "books",
                "full"
            )
        )
    }

/*    fun sendBookId(newBookId: String) {
        bookId.value = newBookId
    }*/
}