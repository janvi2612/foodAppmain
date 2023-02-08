package com.example.foodapp.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.Util.DiffUtilExt
import com.example.foodapp.databinding.ItemRvMainCategoryBinding
import com.example.foodapp.fragments.home.HomeAdapter.MyViewHolder
import com.example.foodapp.model.Recipe

class HomeAdapter(val onMainClick: (Recipe) -> Unit) :RecyclerView.Adapter<MyViewHolder>() {

    private var items = emptyList<Recipe>()



    class MyViewHolder(private val binding:ItemRvMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            currentItem:Recipe,

            onMainClick: (Recipe) -> Unit) {
              binding.photoModel=currentItem
              binding.root.setOnClickListener{
                  onMainClick(currentItem)
            }
        }
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutManager = LayoutInflater.from(parent.context)
                 val binding=ItemRvMainCategoryBinding.inflate(layoutManager,parent,false)
                return MyViewHolder(binding)
            }

        }


}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = items.getOrNull(position)
        currentItem?.let {
            holder.bind(it,onMainClick)
        }

    }

    override fun getItemCount(): Int {
    return items.size
    }
    fun setData(newList: List<Recipe>){
        val userDiffUtil = DiffUtilExt(items, newList)
        val userDiffUtilResult = DiffUtil.calculateDiff(userDiffUtil)
        items = newList
        userDiffUtilResult.dispatchUpdatesTo(this)
    }
}
