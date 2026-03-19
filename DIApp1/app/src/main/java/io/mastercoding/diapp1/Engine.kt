package io.mastercoding.diapp1

import javax.inject.Inject

//@Inject: tells hilt how create an instance of engine
class Engine @Inject constructor() {
    fun start()="Engine started"
}