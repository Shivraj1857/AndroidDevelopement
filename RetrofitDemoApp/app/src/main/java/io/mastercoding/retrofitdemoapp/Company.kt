package io.mastercoding.retrofitdemoapp

import com.google.gson.annotations.SerializedName

//json file madalyala variable cha format lihavayacha pojo class pan mh
data class Company(
    @SerializedName("name")
    val name: String,

    @SerializedName("countryCode")
    val countryCode: String,

    @SerializedName("market_cap")
    val marketCap: Double,

    @SerializedName("id")
    val id:Int
)
