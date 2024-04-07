package pe.edu.crisol.libreria.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import pe.edu.crisol.libreria.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        val searchBar = binding.searchBar
        val searchView = binding.searchView

        searchView.setupWithSearchBar(searchBar)

        searchView.editText.addTextChangedListener {
            text ->
            if (text!!.isNotEmpty())
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }


        searchView.editText.setOnEditorActionListener { v, actionId, event ->
            searchBar.setText(searchView.getText());
            searchView.hide();
            return@setOnEditorActionListener false
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}