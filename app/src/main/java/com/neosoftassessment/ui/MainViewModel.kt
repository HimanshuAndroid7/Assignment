package com.neosoftassessment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neosoftassessment.data.MainRepository

class MainViewModel constructor(private val repository: MainRepository) :
    ViewModel() {

    val playersList = MutableLiveData<ArrayList<String>>()
    val bannersList = MutableLiveData<ArrayList<Int>>()

    fun getPlayersList(team:String,players:Int){
        playersList.postValue(repository.players(team,players))
    }

    fun getBannersList(values:Int){
       bannersList.postValue(repository.banners(values))
    }

}