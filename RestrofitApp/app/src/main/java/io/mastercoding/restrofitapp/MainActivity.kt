package io.mastercoding.restrofitapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //val textView=findViewById<TextView>(R.id.text1)


        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.text1)

        val retrofitService= RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val responeLiveData: LiveData<Response<Albums>> =
            liveData {
                //val response=retrofitService.getAlbums()
                val response2=retrofitService.getSpecificAlbums(6)
                emit(response2)
            }

        responeLiveData.observe(this, Observer{
            val albumsList=it.body()?.listIterator()

            if(albumsList!=null){
                while (albumsList.hasNext()){
                    val albumItem=albumsList.next()
                    //Log.i("TAGY",albumItem.title)

                    val result="Album Title: ${albumItem.title} \n"
                    tv?.append(result)

                }
            }
        })

    }
}