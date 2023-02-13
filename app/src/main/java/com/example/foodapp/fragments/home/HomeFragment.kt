package com.example.foodapp.fragments.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.Util.NetworkResult
import com.example.foodapp.Util.snackBar
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.model.Result
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()


    private var lastQuery = "";

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        setUpUi()
        viewModel.getAllReciperesponse()
        setUpObservers()

        return binding.root
    }

    private fun setUpUi() {
        adapter = HomeAdapter(onMainClick = {
            // Timber.e(it.title)
            // it.id.toString()
            Toast.makeText(requireContext(), "${it.title}", Toast.LENGTH_LONG).show()
            it.id?.let { it ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragment3ToDetailFragment(it)
                )
            }

        }

        )

        binding.recyclerView.shimmerLayout = R.layout.shimmer_layout
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty() && query != lastQuery) {
                    lastQuery = query
                    viewModel.getPost(query.toString())
                }

                return true


            }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty() && newText != lastQuery) {
            lastQuery = newText
            viewModel.getPost(newText.toString())
        }

        return true
    }




        })


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpObservers() {
        viewModel.myrresponse.observe(viewLifecycleOwner , androidx.lifecycle.Observer{ response ->
            when (response) {
                is NetworkResult.Error -> {
                    response.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {

                    binding.recyclerView.showShimmer()
//                    Handler().postDelayed(Runnable {
//                        binding.recyclerView.showShimmer()// to hide shimmer
//                    } as Runnable, 3000)
                }
                is NetworkResult.Success -> {

                    response.data?.results.let { recipe ->
                        //binding.recyclerView.hideShimmer()
                        binding.recyclerView.hideShimmer()
                        adapter.setData(recipe?.filterNotNull() ?: emptyList())
                    }
                }
                else -> {}
            }

            })


    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}












