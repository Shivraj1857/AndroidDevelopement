package io.mastercoding.viewmodelapp

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
     var counter= MutableLiveData<Int>()
    init {
        counter.value=0
    }

    fun increamentCounter(view: View){
        counter.value=counter.value?.plus(1)
    }

    //getter method hi counter chi value ghyanasathi




}