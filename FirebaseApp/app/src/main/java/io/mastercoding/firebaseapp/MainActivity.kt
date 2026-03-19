package io.mastercoding.firebaseapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var text1=findViewById<TextView>(R.id.textView)
        database = Firebase.database.reference

        //Witing object to firebase
        val user1= User("shiva","shiva1234")
        database.child("user").setValue(user1)

        //Reading object from fire base
        val pe=object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val u1=snapshot.getValue<User>()
                text1.text=u1.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.child("user ").addValueEventListener(pe)



     //writing and reading from database
//        var text1: TextView=findViewById(R.id.textView)
//        //database ch reference
//        //https://console.firebase.google.com/project/fir-kotlin-8523b/database/fir-kotlin-8523b-default-rtdb/data/~2F
//        database = Firebase.database.reference
//
//        //liha database madhye
//        database madhye lihatani apan key:vaule madhye lihat asato
//        database.child("Price").setValue("1920 $")
//
//        //database madhun vacha
//        val postListener=object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val gold_price=snapshot.value
//                text1.text=gold_price.toString()
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        }
//        database.child("Price").addValueEventListener(postListener)

    }
}