package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.crisol.libreria.databinding.FragmentHomeBinding
import pe.edu.crisol.libreria.view.adapters.BookCarouselAdapter
import pe.edu.crisol.libreria.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

/*        val carouselRecyclerView = binding.carouselRecyclerView
        val carouselRecyclerView2 = binding.carouselRecyclerView2
        val carouselRecyclerView3 = binding.carouselRecyclerView3
        val carouselRecyclerView4 = binding.carouselRecyclerView4*/


        loadAll()

        return view
    }


    private fun loadAll() {
        viewModel.loadCategory("computers").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null)
                binding.carouselRecyclerView.adapter = BookCarouselAdapter(response.items)
        })
        viewModel.loadCategory("Fiction").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null)
                binding.carouselRecyclerView2.adapter = BookCarouselAdapter(response.items)
        })
        viewModel.loadCategory("Science").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null)
                binding.carouselRecyclerView3.adapter = BookCarouselAdapter(response.items)
        })
        viewModel.loadCategory("History").observe( viewLifecycleOwner, Observer { response ->
            if (response!=null)
                binding.carouselRecyclerView4.adapter = BookCarouselAdapter(response.items)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}