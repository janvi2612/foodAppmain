package com.example.foodapp.fragments.home

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.foodapp.R

import com.example.foodapp.Util.NetworkResult
import com.example.foodapp.model.Result

import com.example.foodapp.repository.Repository
import com.example.startertemplate.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository,application: Application):
    BaseViewModel(application) {

    private val mContext=application
    private val _myrresponse = MutableLiveData<NetworkResult<com.example.foodapp.model.Result>?>()
    val myrresponse: MutableLiveData<NetworkResult<Result>?>
        get() = _myrresponse




    init {
        Timber.e("init")
        getAllReciperesponse()
    }
    @RequiresApi(Build.VERSION_CODES.M)


    fun getPost(query :String) {
       viewModelScope.launch {
           _myrresponse.value=NetworkResult.Loading()
          if(isConnected()){
              val response2 :Response<com.example.foodapp.model.Result> = repository.getQuery(query)
              _myrresponse.value = handleResponse(response2)
          }
           else{
              _myrresponse.value = NetworkResult.Error(
                  mContext.getString(R.string.no_internet)
              )
          }
       }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getAllReciperesponse() {

        viewModelScope.launch {
            _myrresponse.value=NetworkResult.Loading()
            if(isConnected()){
                val response3 :Response<com.example.foodapp.model.Result> = repository.getAllRecipe()
                _myrresponse.value = handleResponse(response3)
            }
            else{
                _myrresponse.value = NetworkResult.Error(
                    mContext.getString(R.string.no_internet)
                )
            }
        }


    }


}

