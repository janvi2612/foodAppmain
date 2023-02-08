package com.example.foodapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        setUpUi()
        viewModel.getAllRecipe2()
        setUpObservers()

        return binding.root
    }
    private fun setUpUi() {
        adapter = HomeAdapter(  onMainClick = {
            // it.id.toString()
            Toast.makeText(requireContext(), "${it.title}", Toast.LENGTH_LONG).show()
            it.id?.let { it->
                findNavController().navigate(
                HomeFragmentDirections.actionHomeFragment3ToDetailFragment(it)
               )
            }

        }

            )

       binding.recyclerView.layoutManager=GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter=adapter


    }

    private fun setUpObservers() {
        viewModel.myResponse2.observe(viewLifecycleOwner , Observer {
            it.results?.let { recipe->
                adapter.setData(recipe.filterNotNull())
            }
        })
        viewModel.myResponse5.observe(viewLifecycleOwner, Observer {
            it.results?.let {recipe->

                adapter.setData(recipe.filterNotNull())
            }
        })
    }
}












