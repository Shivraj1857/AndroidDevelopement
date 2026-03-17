package io.mastercoding.restrofitapp

import kotlinx.serialization.SerialName


//first step in rertofit as data class(POOJO class)
//ha class json object sathi
data class AlbumItem(
    @SerialName("id")
    val id: Int,

    @SerialName("userId")
    val userId: Int,

    @SerialName("title")
    val title: String
)
