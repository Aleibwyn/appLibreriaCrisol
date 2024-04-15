package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.FragmentDetailsBinding
import pe.edu.crisol.libreria.model.WishList
import pe.edu.crisol.libreria.viewModel.DetailsViewModel
import pe.edu.crisol.libreria.viewModel.DetailsViewModelFactory

class DetailsFragment : Fragment(), MenuProvider {

    private var _binding: FragmentDetailsBinding ? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailsViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val toolbar =binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(toolbar, navController)

        val bookId = DetailsFragmentArgs.fromBundle(requireArguments()).bookId

        val viewModelFactory = DetailsViewModelFactory(bookId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        viewModel.searchBookById(bookId).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(view.context)
                    .load(it.volumeInfo.imageLinks.thumbnail)
                    .into(binding.bookCoverDetails)
                binding.bookTitleDetails.text = it.volumeInfo.title
                binding.bookAuthorsDetails.text = it.volumeInfo.authors.joinToString()
                binding.bookPriceDetails.text = "${it.saleInfo.listPrice.currencyCode} ${it.saleInfo.listPrice.amount}"
                binding.titleDescription.text = "About this book"
                binding.contentDescription.text = Html.fromHtml(it.volumeInfo.description, Html.FROM_HTML_MODE_COMPACT)
            }
        })

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid

        return view
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.add_to_wishlist -> saveWishList(uid, viewModel.bookId)
        }
        return false
    }

    fun saveWishList(userid: String, bookId: String){
        database = FirebaseDatabase.getInstance().getReference("WishList")
        val wishList = WishList(userid, bookId)
        database.child(userid).child(bookId).setValue(wishList).addOnSuccessListener {
            Snackbar.make(binding.root, "In Wishlist", Snackbar.LENGTH_SHORT).show()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}