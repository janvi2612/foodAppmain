package com.example.foodapp.repository

import com.example.foodapp.api.RetrofitInstance
import com.example.foodapp.api.ApiService
import com.example.foodapp.model.Detail
import com.example.foodapp.model.Recipe
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api : ApiService
) {
    suspend fun getQuery(query:String): Response<com.example.foodapp.model.Result>
    {
        return api.getQuery(query)
    }
    suspend fun getAllRecipe() : Response<com.example.foodapp.model.Result>
    {
        return api.getAllRecipe()
    }
    suspend fun  getRecipeDetail(id:Int): Response<Detail> {
        return api.getRecipeDetail(id)
    }

    suspend fun getRecipe():Response<Recipe>{
        return api.getRecipe()
    }
}