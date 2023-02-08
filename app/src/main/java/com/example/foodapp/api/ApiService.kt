package com.example.foodapp.api


import com.example.foodapp.model.Detail
import com.example.foodapp.model.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/complexSearch?apiKey=9d1751511fdd4ef0b31098f7a87264ec")
    suspend fun getQuery(@Query("query") query: String): Response<com.example.foodapp.model.Result>


    @GET("recipes/complexSearch?apiKey=9d1751511fdd4ef0b31098f7a87264ec&query=salad&number=20")
    suspend fun getAllRecipe() : Response<com.example.foodapp.model.Result>

    @GET("recipes/{id}/information?includeNutrition=true&apiKey=9d1751511fdd4ef0b31098f7a87264ec")
    suspend fun getRecipeDetail(
        @Path("id") id:Int
    ): Response<Detail>
    @GET("recipes/716429/information?includeNutrition=false&apiKey=9d1751511fdd4ef0b31098f7a87264ec")
    suspend fun getRecipe() : Response<Recipe>


}



