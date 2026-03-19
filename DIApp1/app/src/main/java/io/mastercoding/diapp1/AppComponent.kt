package io.mastercoding.diapp1

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    //car class sathi dragger depedency inject kar(car class laganari)
    fun inject(car:Car)
}

//package io.mastercoding.diapp1
//
//import dagger.Component
//import javax.inject.Singleton
//module->bridge->class
//@Singleton
//@Component(modules = [AppModule::class])
//interface AppComponent {
//
//    fun getCar(): Car
//}