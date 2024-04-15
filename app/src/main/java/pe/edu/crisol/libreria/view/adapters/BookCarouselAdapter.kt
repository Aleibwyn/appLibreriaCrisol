package pe.edu.crisol.libreria.view.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.ItemBookCarouselBinding
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.view.BookDiffItemCallback

class BookCarouselAdapter(private val clickListener: (bookId: String) -> Unit) :
    ListAdapter<Book, BookCarouselAdapter.ViewHolder>(BookDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)

    }

    class ViewHolder(val binding: ItemBookCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, clickListener: (bookId: String) -> Unit) {
            item.volumeInfo.title?.let {
                binding.bookTitle.text = it
            }
            item.volumeInfo.imageLinks?.let {
                Glide.with(itemView.context)
                    .load(it.thumbnail)
                    .into(binding.carouselImageView)
            }

            binding.root.setOnClickListener {
                clickListener(item.id)
            }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): BookCarouselAdapter.ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemBookCarouselBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}