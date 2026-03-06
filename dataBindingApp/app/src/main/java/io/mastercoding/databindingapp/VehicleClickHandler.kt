package io.mastercoding.databindingapp

import android.content.Context
import android.view.View
import android.widget.Toast

class VehicleClickHandler(var context: Context) {
    fun display(view: View){
        Toast.makeText(context,"Btn is clicked", Toast.LENGTH_LONG).show()
    }
}