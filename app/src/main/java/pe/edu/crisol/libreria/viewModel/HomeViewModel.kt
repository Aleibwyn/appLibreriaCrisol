package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.repository.SearchRepository
import pe.edu.crisol.libreria.retrofit.request.SearchRequest
import pe.edu.crisol.libreria.retrofit.response.SearchResponse

class HomeViewModel : ViewModel() {
    /*private val repository = SearchRepository()*/
    /*fun loadCategory(category: String): LiveData<SearchResponse> {
        return SearchRepository().searchBooks(
            SearchRequest(
                "subject:${category}",
                "partial",
                "",
                40,
                "relevance",
                "all",
                "full"
            )
        )
    }
*/
    fun loadCategory(category: String): LiveData<SearchResponse> {
        return SearchRepository().searchByCategory(category)
    }
}