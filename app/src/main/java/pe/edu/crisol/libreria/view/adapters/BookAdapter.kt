package pe.edu.crisol.libreria.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.ItemBookBinding
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.viewModel.HomeViewModel
import pe.edu.crisol.libreria.viewModel.SearchViewModel

class BookAdapter(private val books: List<Book>, private val searchViewModel: SearchViewModel) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (books.isNotEmpty()) {
            with(holder) {
                with(books[position]) {
                    volumeInfo.title?.let { title ->
                        binding.bookTitle.text = title
                    }
                    volumeInfo.authors?.let { authors ->
                        binding.bookAuthor.text = authors.joinToString()
                    }

                    saleInfo.listPrice?.let { listPrice ->
                        "${listPrice.currencyCode} ${listPrice.amount}".also { binding.bookPrice.text = it }
                    }

                    volumeInfo.imageLinks?.let {
                        Glide.with(itemView.context)
                            .load(volumeInfo.imageLinks.smallThumbnail)
                            .fitCenter()
                            .into(binding.bookCover)
                    }

                    itemView.setOnClickListener {
                        Toast.makeText(it.context, volumeInfo.toString(), Toast.LENGTH_LONG).show()
                        searchViewModel.sendBookId(id)
                    }
                }

            }
        }
    }
}