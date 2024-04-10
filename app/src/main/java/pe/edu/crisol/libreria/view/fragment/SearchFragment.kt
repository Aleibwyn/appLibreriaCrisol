package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.crisol.libreria.view.adapters.BookAdapter
import pe.edu.crisol.libreria.databinding.FragmentSearchBinding
import pe.edu.crisol.libreria.viewModel.SearchViewModel
import retrofit2.Retrofit

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        //Setup SearchView and SearchBar
        val searchBar = binding.searchBar
        val searchView = binding.searchView

        searchView.setupWithSearchBar(searchBar)

        val booksRecyclerView = binding.booksRecyclerView

        booksRecyclerView.layoutManager = LinearLayoutManager(activity)


        viewModel.searchResponse.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue?.items!=null)
                booksRecyclerView.adapter = BookAdapter(newValue.items)
        })


        searchView.editText.setOnEditorActionListener { v, actionId, event ->
            viewModel.searchBooks(searchView.text.toString())
            searchBar.setText(searchView.text)
            searchView.hide()
            return@setOnEditorActionListener false
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}