package io.mastercoding.hiltapp

import javax.inject.Inject

//handle provide greeting message
class GreetingReposistory @Inject constructor() {
    fun sayHello(): String{
        return "Hello from Hilt"
    }
}