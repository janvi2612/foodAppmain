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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class DetailFragment : Fragment() {

   private lateinit var binding: FragmentDetailBinding
   private lateinit var adapter: DetailAdapter
   private  val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentDetailBinding.inflate(inflater, container, false)

        setupUi()
       setUpObservers()
        return binding.root
    }

    private fun setupUi() {
       adapter= DetailAdapter()
        binding.recycleviewIndi.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recycleviewIndi.adapter=adapter
        args.id.let {
            Timber.e(it.toString())
            viewModel.getRecipeDetail(it)
        }

    }
  @RequiresApi(Build.VERSION_CODES.N)
  @SuppressLint("StringFormatInvalid", "SuspiciousIndentation")
    private fun setUpObservers() {
     viewModel.myResponse.observe(viewLifecycleOwner, Observer {
         it.let{
             it.body().let{
                 binding.dtlViewModel = it
                 val decimalf= DecimalFormat("#.##")
                 it?.nutrition?.nutrients?.forEach {
                     //var per=it.amount!!*it.percentOfDailyNeeds!!/100
                     when(it.name){
                        " Carbohydrates " ->
                        {
                            Timber.e("Carbohydrates ${it.percentOfDailyNeeds}")
                            binding.pgCarb.progress = it.percentOfDailyNeeds!!.toInt()
                            binding.carbAmount.text = it.amount.toString()
                            binding.carbUnit.text = it.unit
                            binding.txtCarbs.text = this.resources.getString(R.string.percentage_formatt,decimalf.format(it.percentOfDailyNeeds))
                        }
                         "Fat" ->
                         {
                             Timber.e("Fats ${it.percentOfDailyNeeds}")
                             binding.fatAmount.text=it.amount.toString()
                             binding.pgFat.progress=it.percentOfDailyNeeds!!.toInt()

                             binding.fatUnit.text=it.unit
                             binding.fatPer.text=this.resources.getString(R.string.percentage_formatt,decimalf.format(it.percentOfDailyNeeds))
                         }
                         "Protien" ->
                         {
                             Timber.e("Protien ${it.percentOfDailyNeeds}")
                             binding.pgProtien.progress=it.percentOfDailyNeeds!!.toInt()
                             binding.proAmount.text=it.amount.toString()
                             binding.proUnit.text=it.unit
                             binding.proPer.text=this.resources.getString(R.string.percentage_formatt,decimalf.format(it.percentOfDailyNeeds))
                         }
                         "Calories" -> {
                             Timber.e("Calories ${it.percentOfDailyNeeds}")
                             binding.pgCal.progress=it.percentOfDailyNeeds!!.toInt()
                             binding.calAmount.text=it.amount.toString()
                             binding.calUnit.text=it.unit

                         }
                     }
                 }
                 var url = it?.sourceUrl
                 binding.SeedetailRecipe.setOnClickListener{
                     var intent = Intent(Intent.ACTION_VIEW)
                     intent.data = Uri.parse(url)
                     startActivity(intent)
                 }
                 it?.extendedIngredients?.let {response->
                     adapter.setData(response)
                 }

             }
             }


     })

    }


}