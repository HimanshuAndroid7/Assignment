package com.neosoftassessment.data

import com.neosoftassessment.R

class MainRepository {

    private var playersList : ArrayList<String>? = null
    private var bannersList : ArrayList<Int>? = null

    fun players(team:String, player: Int): ArrayList<String> {
        playersList = ArrayList<String>()
        for (item in 0 until player){
            playersList!!.add(team+item)
        }
        return playersList as ArrayList<String>
    }

    fun banners(values: Int): ArrayList<Int> {
        bannersList = ArrayList<Int>()
        for (item in 0 until values){
            bannersList!!.add(R.mipmap.ic_launcher)
        }
        return bannersList as ArrayList<Int>
    }

}