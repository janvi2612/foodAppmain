package com.example.foodapp.fragments.listingridents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.Util.DiffUtilExt
import com.example.foodapp.databinding.IngridentsListBinding
import com.example.foodapp.model.ExtendedIngredient

class ListIngredientAdapter :RecyclerView.Adapter<ListIngredientAdapter.MyViewHolder>() {
    private  var itemlist= emptyList<ExtendedIngredient>()

    class MyViewHolder(private val  binding: IngridentsListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem : ExtendedIngredient){
            binding.indilist=currentItem
        }
       companion object{
           fun from(parent: ViewGroup):MyViewHolder{
               val layoutInflater = LayoutInflater.from(parent.context)
               val binding=IngridentsListBinding.inflate(layoutInflater,parent,false)
               return ListIngredientAdapter.MyViewHolder(binding)

           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=itemlist.getOrNull(position)
        currentItem?.let {
            holder.bind(it)
        }



    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
    fun setData(newList : List<ExtendedIngredient>){
       val diffUtil=DiffUtilExt(itemlist,newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        itemlist=newList
        diffResult.dispatchUpdatesTo(this)
    }

}