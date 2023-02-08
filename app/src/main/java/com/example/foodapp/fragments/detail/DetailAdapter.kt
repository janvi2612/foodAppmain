package com.example.foodapp.fragments.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.Util.DiffUtilExt
import com.example.foodapp.databinding.CustomIndigredentsBinding

import com.example.foodapp.model.ExtendedIngredient

class DetailAdapter :RecyclerView.Adapter<DetailAdapter.MyViewHolder>() {
    private var itemList= emptyList<ExtendedIngredient>()
    private val limit = 5
    class MyViewHolder(private val binding : CustomIndigredentsBinding):RecyclerView.ViewHolder(binding.root) {
      fun bind(currentItem : ExtendedIngredient){
          binding.ingredientModel = currentItem
      }
        companion object{
            fun from(parent: ViewGroup): DetailAdapter.MyViewHolder {
                val layoutManager = LayoutInflater.from(parent.context)
                val binding= CustomIndigredentsBinding.inflate(layoutManager,parent,false)
                return DetailAdapter.MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     val currentItem=itemList.getOrNull(position)
     currentItem?.let {
         holder.bind(it)
     }
    }
    override fun getItemCount(): Int {
     if(itemList.size>limit){
         return limit
     }
        else{
         return itemList.size
     }
    }
    fun setData(recipeResponse: List<ExtendedIngredient>){
        val diffUtil = DiffUtilExt(itemList,recipeResponse)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        itemList=recipeResponse
        diffResult.dispatchUpdatesTo(this)

    }

}




