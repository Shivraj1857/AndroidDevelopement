package io.mastercoding.retrofitdemoapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var resultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView=findViewById(R.id.result)

    //Retrofit instance
    val retrofitInstance=RetrofitInstance.getRetrofitInstance()
        .create(CompanyService::class.java)

    //live data
    val responseLiveData: LiveData<Response<CompanyList>> =
        liveData{
            val countryRespone=retrofitInstance.getAllCompanies()
            emit(countryRespone)

        }

        responseLiveData.observe(this, Observer{
            val companiesList=it.body()?.listIterator()

            if(companiesList!=null){
                while (companiesList.hasNext()){
                    val companyItem=companiesList.next()

                    val companyResult="Company Name:${companyItem.name}\n"
                    resultTextView.append(companyResult)
                }
            }
        })


    }
}