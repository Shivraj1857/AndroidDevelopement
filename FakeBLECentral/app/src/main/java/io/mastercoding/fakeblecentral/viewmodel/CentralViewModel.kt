package io.mastercoding.fakeblecentral.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.mastercoding.fakeblecentral.data.BleCentralRepository

class CentralViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val repo = BleCentralRepository(app)

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun scan() {
        Log.d("CentralVM", "scan called")
        repo.connectAndRead(getApplication<Application>()) { value ->
            Log.d("CentralVM", "received: $value")
            _data.postValue(value)
        }
    }

}