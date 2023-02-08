package com.example.foodapp.fragments.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.R

import com.example.foodapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository,application: Application):ViewModel() {
    val myResponse2: MutableLiveData<com.example.foodapp.model.Result> = MutableLiveData()
    val myResponse5: MutableLiveData<com.example.foodapp.model.Result> = MutableLiveData()
    private val mContext=application
    fun getPost() {
        viewModelScope.launch {
            viewModelScope.launch {
            val response : Response<com.example.foodapp.model.Result> =repository.getQuery(query="salad")
            if (response.isSuccessful) {
                response.body().let {
                    myResponse2.value=it
                }
            }
        }
    }
    }
    fun getAllRecipe2(){

        viewModelScope.launch {
            val response5 : Response<com.example.foodapp.model.Result> = repository.getAllRecipe()
            if (response5.isSuccessful){
                response5.body().let {
                    myResponse5.value = it
                }
            }
            else{
                mContext.getString(R.string.no_internet)
            }

        }

    }
}

