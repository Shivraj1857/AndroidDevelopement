package io.mastercoding.diapp1

fun main(){
    //hilt and dragger component generate a Dragger componenet
    //class at a compile ime
    val appComponent: AppComponent = DaggerAppComponent.create()

    //retrieve car instance
    val car=Car()

    //inject dependensy into car field
    appComponent.inject(car)

    //use the car
    car.drive()
}
//package io.mastercoding.diapp1
//
//fun main(){
//    //hilt and dragger component generate a Dragger componenet
//    //class at a compile ime
//    val appComponent: AppComponent = DaggerAppComponent.create()
//
//    //retrieve car instance
//    val car:Car=appComponent.getCar()
//
//    //use the car
//    println(car.drive())
//}