package io.mastercoding.databindingapp

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class Vehicle(): BaseObservable(){
    var modelyear: String=""

    @get:Bindable
    var name: String=""
        set(value) {
            field=value
            notifyPropertyChanged(BR.name)
        }
}
