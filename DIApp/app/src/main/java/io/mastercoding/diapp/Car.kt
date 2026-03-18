package io.mastercoding.diapp

class Car {

    //dependency in thi class
    //car class create its own engine
    //private val engine= Engine()

    fun drive(){
        println(Engine().start())
    }

}