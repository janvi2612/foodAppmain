package com.example.foodapp.fragments.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.model.Detail
import com.example.foodapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor
    (private val repository: Repository,application: Application):ViewModel()

{
    val myResponse: MutableLiveData<Response<Detail>> = MutableLiveData()

    fun  getRecipeDetail(id : Int){
        viewModelScope.launch {
            val response1 : Response<Detail> = repository.getRecipeDetail(id)
            myResponse.value=response1
        }
    }

}

