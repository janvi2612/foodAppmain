package com.example.foodapp.fragments.detail

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.R
import com.example.foodapp.Util.NetworkResult
import com.example.foodapp.model.Detail
import com.example.foodapp.repository.Repository
import com.example.startertemplate.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor
    (private val repository: Repository,application: Application):BaseViewModel(application)

{
      private val mContext = application
    private var vid = -1
    val myResponse: MutableLiveData<NetworkResult<Detail>?> = MutableLiveData()
@RequiresApi(Build.VERSION_CODES.M)
    fun  getRecipeDetail(id : Int){
    if (vid == id) {
        return
    } else {
        vid = id
    }
        viewModelScope.launch {
            if (isConnected()) {


                myResponse.value = NetworkResult.Loading()
                val responsedetail: Response<Detail> = repository.getRecipeDetail(id)
                myResponse.value = handleResponse(responsedetail)



            } else {
                Toast.makeText(mContext, R.string.no_internet, Toast.LENGTH_SHORT).show()
            }
        }
    }

}

