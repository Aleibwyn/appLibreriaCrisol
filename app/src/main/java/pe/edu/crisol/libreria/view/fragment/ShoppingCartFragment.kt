package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.FragmentShoppingCartBinding
import pe.edu.crisol.libreria.view.adapters.CartBookAdapter
import pe.edu.crisol.libreria.viewModel.ShoppingCartViewModel

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var adapter: CartBookAdapter
    private lateinit var shoppingCartViewModel: ShoppingCartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root

        shoppingCartViewModel = ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)

        adapter = CartBookAdapter(shoppingCartViewModel.getCartBooks())
        binding.cartRecyclerView.adapter = adapter
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.tvProductCount.text = getString(R.string.product_count, shoppingCartViewModel.getTotalProductCount())
        binding.tvTotalPrice.text = getString(R.string.tv_total_price, shoppingCartViewModel.getTotalPrice())

        return view
    }

    private fun updateProductCountAndTotalPrice() {

    }
}