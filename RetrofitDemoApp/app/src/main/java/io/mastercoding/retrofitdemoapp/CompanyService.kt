package io.mastercoding.retrofitdemoapp

import retrofit2.Response
import retrofit2.http.GET

interface CompanyService {

        @GET("/companies")
       suspend fun getAllCompanies(): Response<CompanyList>

}