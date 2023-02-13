package com.example.foodapp.fragments.listingridents

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.Util.NetworkResult
import com.example.foodapp.Util.snackBar
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.databinding.FragmentListingredientsBinding
import com.example.foodapp.fragments.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListIngredientsFragment : Fragment() {
    private var _binding: FragmentListingredientsBinding? = null
    private val binding: FragmentListingredientsBinding
        get() = _binding!!
    private lateinit var adapter: ListIngredientAdapter
    private val viewModel:DetailViewModel by viewModels()
    private val args by navArgs<ListIngredientsFragmentArgs>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentListingredientsBinding.inflate(inflater, container, false)
        setUpi()
        setupobserver()
        return binding.root


    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpi() {
        binding.listrecyclerview.shimmerLayout = R.layout.list_placeholder
        adapter=ListIngredientAdapter()
        binding.listrecyclerview.layoutManager=LinearLayoutManager(requireContext())
        binding.listrecyclerview.adapter=adapter
          args.id?.let {
         viewModel.getRecipeDetail(it)
     }
    }

    private fun setupobserver() {
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response->
            when(response) {
                is NetworkResult.Error -> {
                    response.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {
                    binding.listrecyclerview.showShimmer()


                }
                is NetworkResult.Success -> {
                    binding.listrecyclerview.hideShimmer()
                    response.data.let { detail ->

                        detail?.extendedIngredients?.let { exi ->
                            adapter.setData(exi)
                        }

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


