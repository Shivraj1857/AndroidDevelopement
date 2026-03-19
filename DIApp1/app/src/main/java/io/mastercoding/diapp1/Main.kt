package io.mastercoding.diapp1

fun main(){
    val engine= Engine()
    val car=Car(engine)
    //depedency injected here
    println(car.drive())


}