package com.example.foodapp.fragments.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.Util.NetworkResult
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DetailAdapter
   private  val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         _binding = FragmentDetailBinding.inflate(inflater, container, false)

        setupUi()
       setUpObservers()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupUi() {
       adapter= DetailAdapter()
        binding.recycleviewIndi.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.backbtn.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.recycleviewIndi.adapter=adapter
        args.id.let {
            Timber.e(it.toString())
            viewModel.getRecipeDetail(it)
        }

    }
  @RequiresApi(Build.VERSION_CODES.N)
  @SuppressLint("StringFormatInvalid", "SuspiciousIndentation")
    private fun setUpObservers() {
      viewModel.myResponse.observe(viewLifecycleOwner, Observer {response->
          when(response) {
              is NetworkResult.Error<*> -> {
                  response.message
              }
              is NetworkResult.Loading<*> -> {

              }

              is NetworkResult.Success<*> -> {
                  response.data.let { detail ->
                      binding.dtlViewModel = detail
                      val decimalf = DecimalFormat("#.##")
                      detail?.nutrition?.nutrients?.forEach { nut ->
                          when (nut.name) {
                              " Carbohydrate " -> {
                                  Timber.e("Carbohydrates ${nut.percentOfDailyNeeds}")
                                  binding.pgCarb.progress = nut.percentOfDailyNeeds!!.toInt()
                                  binding.carbAmount.text = nut.amount.toString()
                                  binding.carbUnit.text = nut.unit
                                  binding.carbPer.text = this.resources.getString(
                                      R.string.percentage_formatt,
                                      decimalf.format(nut.percentOfDailyNeeds)
                                  )
                              }
                              "Fat" -> {
                                  Timber.e("Fats ${nut.percentOfDailyNeeds}")
                                  binding.pgFat.progress = nut.percentOfDailyNeeds!!.toInt()
                                  binding.fatAmount.text = nut.amount.toString()
                                  binding.fatUnit.text = nut.unit
                                  binding.fatPer.text = this.resources.getString(
                                      R.string.percentage_formatt,
                                      decimalf.format(nut.percentOfDailyNeeds)
                                  )
                              }
                              "Protien" -> {
                                  Timber.e("Protien ${nut.percentOfDailyNeeds}")
                                  binding.pgProtien.progress = nut.percentOfDailyNeeds!!.toInt()
                                  binding.proAmount.text = nut.amount.toString()
                                  binding.proUnit.text = nut.unit
                                  binding.proPer.text = this.resources.getString(
                                      R.string.percentage_formatt,
                                      decimalf.format(nut.percentOfDailyNeeds)
                                  )
                              }
                              "Calories" -> {
                                  Timber.e("Calories ${nut.percentOfDailyNeeds}")
                                  binding.pgCal.progress = nut.percentOfDailyNeeds!!.toInt()
                                  binding.calAmount.text = nut.amount.toString()
                                  binding.calUnit.text = nut.unit

                              }
                          }
                      }
                      var url = detail?.sourceUrl
                      binding.SeedetailRecipe.setOnClickListener {
                          var intent = Intent(Intent.ACTION_VIEW)
                          intent.data = Uri.parse(url)
                          startActivity(intent)
                      }
                      detail?.extendedIngredients?.let { response ->
                          adapter.setData(response)
                      }
                      var rating: Float = detail?.healthScore!!.toFloat() * 5 / 100
                      binding.ratingBar.rating = rating
                      detail.id?.let { it ->
                          binding.seeDetailsIng.setOnClickListener { responce ->
                              findNavController().navigate(
                                  DetailFragmentDirections.actionDetailFragmentToListIngredientsFragment(
                                      it
                                  )
                              )
                          }
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