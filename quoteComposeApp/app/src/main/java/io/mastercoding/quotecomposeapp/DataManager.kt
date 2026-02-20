package io.mastercoding.quotecomposeapp

import android.R
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import io.mastercoding.quotecomposeapp.models.Quote
import kotlinx.serialization.builtins.ByteArraySerializer

object DataManager {
    //object mule tyatilapan sagalya method direct access karu shakto
    var data=emptyArray<Quote>()
    var currentQuote: Quote?=null
    var currentPage=mutableStateOf(Pages.LISTING)
    var isDataLoaded= mutableStateOf(false)
    fun loadAssetsFromFile(context: Context){
        val inputStream=context.assets.open("Quotes.json")
        val size:Int=inputStream.available()
        val buffer= ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json=String(buffer,Charsets.UTF_8)
        val gson= Gson()
        data=gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value=true

    }

    fun switchPagaes(quote: Quote?){
        if(currentPage.value==Pages.LISTING){
            currentQuote=quote
            currentPage.value=Pages.DETAIL
        }
        else{
            currentPage.value==Pages.LISTING
        }
    }
}