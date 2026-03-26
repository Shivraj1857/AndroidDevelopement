package io.mastercoding.myandroidportfolio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.mastercoding.myandroidportfolio.R
import io.mastercoding.myandroidportfolio.model.AppModel
import io.mastercoding.myandroidportfolio.repository.AppRepository


class AppListViewModel : ViewModel() {

    private val repository = AppRepository()

    private val _appList = MutableLiveData<List<AppModel>>()
    val appList: LiveData<List<AppModel>> = _appList

    init {
        loadApps()
    }

    //init method

    private fun loadApps() {
        _appList.value = repository.getAppList()
    }
}
