package io.mastercoding.quadraticequationsolver

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import io.mastercoding.quadraticequationsolver.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MyEquation(var binding: ActivityMainBinding) : BaseObservable() {
    @Bindable
    var a: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.a)
        }

    @Bindable
    var b: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.b)
        }

    @Bindable
    var c: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.c)
        }

    fun solveEquation(view: View) {
        var a = a.toInt()
        var b =b.toInt()
        var c=c.toInt()


        //calculate decriment

        var descriminant=((b*b)-(4*a*c))


    //solution

        var x1: Double
        var x2: Double

        if(descriminant>0)
        {
            x1=(-b+ sqrt(descriminant.toDouble()))/(2*a)
            x2=(-b- sqrt(descriminant.toDouble()))/(2*a)

            //dislay result

            binding.result.text="X1=$x1 and X2=$x2"

        }
        else if(descriminant<0){
            binding.result.text="No real root"
        }
        else{
            x1=-b/(2*a).toDouble()
            binding.result.text="x=$x1"

        }




    }
}

