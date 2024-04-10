package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.FragmentDetailsBinding
import pe.edu.crisol.libreria.viewModel.DetailsViewModel
import pe.edu.crisol.libreria.viewModel.DetailsViewModelFactory

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding ? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        val bookId = DetailsFragmentArgs.fromBundle(requireArguments()).bookId

        val viewModelFactory = DetailsViewModelFactory(bookId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        viewModel.searchBookById(bookId).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(view.context)
                    .load(it.volumeInfo.imageLinks.thumbnail).into(binding.bookCoverDetails)
                binding.bookTitleDetails.text = it.volumeInfo.title
                binding.bookAuthorsDetails.text = it.volumeInfo.authors.joinToString()
                binding.bookPriceDetails.text = it.saleInfo.listPrice.amount.toString()
                binding.titleDescription.text = "About this book"
                binding.contentDescription.text = it.volumeInfo.description
            }
        })


        return view
    }
}