package pe.edu.crisol.libreria.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import pe.edu.crisol.libreria.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.toolbar

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}