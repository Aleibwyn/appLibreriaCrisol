package pe.edu.crisol.libreria.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.ItemBookCarouselBinding
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.viewModel.HomeViewModel
import pe.edu.crisol.libreria.viewModel.SearchViewModel

class BookCarouselAdapter(private val booksByCategory: List<Book>, private val searchViewModel: SearchViewModel): RecyclerView.Adapter<BookCarouselAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBookCarouselBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemBookCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(booksByCategory[position]) {
                volumeInfo.imageLinks?.let {
                    Glide.with(itemView.context)
                        .load(volumeInfo.imageLinks.thumbnail)
                        .into(binding.carouselImageView)
                }
                itemView.setOnClickListener {
                    searchViewModel.sendBookId(id)
                }
            }
        }
    }

    override fun getItemCount(): Int = booksByCategory.size
}