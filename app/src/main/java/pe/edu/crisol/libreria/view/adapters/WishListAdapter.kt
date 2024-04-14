package pe.edu.crisol.libreria.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.crisol.libreria.databinding.ItemBookBinding
import pe.edu.crisol.libreria.model.Book

class WishListAdapter(private val lista: List<Book>) : RecyclerView.Adapter<WishListAdapter.WishListViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WishListAdapter.WishListViewHolder = WishListViewHolder.from(parent)
    override fun onBindViewHolder(holder: WishListAdapter.WishListViewHolder, position: Int) {
        val book = lista[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
    class WishListViewHolder(val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.bookTitle.text = book.volumeInfo.title
        }

        companion object {
            fun from(parent: ViewGroup): WishListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookBinding.inflate(layoutInflater, parent, false)
                return WishListViewHolder(binding)
            }
        }
    }
}