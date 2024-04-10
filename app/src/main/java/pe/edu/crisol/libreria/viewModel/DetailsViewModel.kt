package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.repository.DetailsRepository
import pe.edu.crisol.libreria.retrofit.request.DetailsRequest
import pe.edu.crisol.libreria.retrofit.response.DetailsResponse

class DetailsViewModel(newBookId: String): ViewModel() {
    val bookId = newBookId
    private val repository = DetailsRepository()

    fun searchBookById(bookId: String): LiveData<DetailsResponse> {
        return repository.searchBookById(DetailsRequest(bookId))
    }
}