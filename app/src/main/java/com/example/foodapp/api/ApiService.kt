package com.example.foodapp.api


import com.example.foodapp.model.Detail
import com.example.foodapp.model.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("recipes/complexSearch?apiKey=4cd93b1b1eea49b89d849a06fd60a6c7")
    suspend fun getQuery(@Query("query") query: String): Response<com.example.foodapp.model.Result>


    @GET("recipes/complexSearch?apiKey=4cd93b1b1eea49b89d849a06fd60a6c7&number=15")
    suspend fun getAllRecipe() : Response<com.example.foodapp.model.Result>

    @GET("recipes/{id}/information?includeNutrition=true&apiKey=4cd93b1b1eea49b89d849a06fd60a6c7")
    suspend fun getRecipeDetail(
        @Path("id") id:Int
    ): Response<Detail>


    @GET("recipes/716429/information?includeNutrition=false&apiKey=4cd93b1b1eea49b89d849a06fd60a6c7")
    suspend fun getRecipe() : Response<Recipe>


}



