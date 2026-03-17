package io.mastercoding.restrofitapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AlbumService {


    //you should specigy the endpoint of url
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getSpecificAlbums(@Query("userId") useId:Int): Response<Albums>



}