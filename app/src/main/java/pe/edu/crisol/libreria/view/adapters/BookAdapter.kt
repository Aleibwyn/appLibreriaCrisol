package pe.edu.crisol.libreria.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.ItemBookBinding
import pe.edu.crisol.libreria.model.Book

class BookAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (books.isNotEmpty()) {
            with(holder.binding) {
                with(books[position]) {
                    volumeInfo.title?.let { title ->
                        bookTitle.text = title
                    }
                    volumeInfo.authors?.let { authors ->
                        bookAuthor.text = authors.joinToString()
                    }

                    saleInfo.listPrice?.let { listPrice ->
                        "${listPrice.currencyCode} ${listPrice.amount}".also { bookPrice.text = it }
                    }

                    Glide.with(holder.itemView.context)
                        .load(volumeInfo.imageLinks.smallThumbnail)
                        .centerCrop()
                        .into(bookCover)
                }
            }
        }
    }
}