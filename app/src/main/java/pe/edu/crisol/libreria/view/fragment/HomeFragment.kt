package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.UncontainedCarouselStrategy
import pe.edu.crisol.libreria.databinding.FragmentHomeBinding
import pe.edu.crisol.libreria.view.adapters.BookCarouselAdapter
import pe.edu.crisol.libreria.viewModel.HomeViewModel
import pe.edu.crisol.libreria.viewModel.SearchViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val toolbar =binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        searchViewModel.bookId.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                view.findNavController().navigate(action)
            }
        })

        loadAll()

        return view
    }


    private fun loadAll() {
        viewModel.loadCategory("Fiction").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null) {
                binding.carouselRecyclerView.setLayoutManager(CarouselLayoutManager(UncontainedCarouselStrategy()))
                binding.carouselRecyclerView.adapter = BookCarouselAdapter(response.items, searchViewModel)
            }
        })
        viewModel.loadCategory("Juvenile+Fiction").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null) {
                binding.carouselRecyclerView2.setLayoutManager(CarouselLayoutManager(UncontainedCarouselStrategy()))

                binding.carouselRecyclerView2.adapter = BookCarouselAdapter(response.items, searchViewModel)
            }
        })
        viewModel.loadCategory("Science").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null) {
                binding.carouselRecyclerView3.setLayoutManager(CarouselLayoutManager(UncontainedCarouselStrategy()))
                binding.carouselRecyclerView3.adapter = BookCarouselAdapter(response.items, searchViewModel)
            }
        })
        viewModel.loadCategory("History").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null) {
                binding.carouselRecyclerView4.setLayoutManager(CarouselLayoutManager(
                    UncontainedCarouselStrategy()
                ))
                binding.carouselRecyclerView4.adapter = BookCarouselAdapter(response.items, searchViewModel)
            }
        })
    }
    override fun onDestroyView() {
        searchViewModel.bookId.value = ""
        super.onDestroyView()
        _binding = null
    }

}