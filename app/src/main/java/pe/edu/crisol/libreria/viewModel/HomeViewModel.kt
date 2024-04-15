package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.repository.SearchRepository

class HomeViewModel : ViewModel() {

    private val searchRepository = SearchRepository()

    private val _category1 = MutableLiveData<List<Book>>()
    val category1: LiveData<List<Book>> = _category1

    private val _category2 = MutableLiveData<List<Book>>()
    val category2: LiveData<List<Book>> = _category2

    private val _category3 = MutableLiveData<List<Book>>()
    val category3: LiveData<List<Book>> = _category3

    private val _category4 = MutableLiveData<List<Book>>()
    val category4: LiveData<List<Book>> = _category4

    private val _navigateToDetails = MutableLiveData<String?>()
    val navigateToDetails: LiveData<String?> = _navigateToDetails

    init {
        loadCategories()
    }

    private fun loadCategories() {
        loadCategory("Literary", _category1)
        loadCategory("Fiction", _category2)
        loadCategory("History", _category3)
        loadCategory("Computers", _category4)
    }

    private fun loadCategory(category: String, liveData: MutableLiveData<List<Book>>) {
        searchRepository.searchBooksByCategory(category).observeForever {
            liveData.value = it
        }
    }

    fun onBookClicked(bookId: String) {
        _navigateToDetails.value = bookId
    }

    fun onDetailsNavigated() {
        _navigateToDetails.value = null
    }
}