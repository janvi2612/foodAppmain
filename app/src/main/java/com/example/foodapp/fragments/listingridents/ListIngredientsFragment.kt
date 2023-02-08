package com.example.foodapp.fragments.listingridents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentListingredientsBinding



class ListIngredientsFragment : Fragment() {
    private lateinit var binding : FragmentListingredientsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentListingredientsBinding.inflate(inflater, container, false)
        return binding.root

    }

}