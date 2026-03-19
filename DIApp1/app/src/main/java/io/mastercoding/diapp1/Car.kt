package io.mastercoding.diapp1

import javax.inject.Inject

class Car {
    //dependecy pass from a outside
    lateinit var engine: Engine

    //hilt will ingest the engine instance into this method
    @Inject
    fun installEngine(engine: Engine){
        this.engine=engine
    }

    fun drive(){
        println(engine.start())
    }
}

//field
//package io.mastercoding.diapp1
//
//import javax.inject.Inject
//
//class Car {
//    //dependecy pass from a outside
//    //field injection engine will injected into field
//    @Inject
//    lateinit var engine: Engine
//    fun drive(){
//        println(engine.start())
//    }
//}

//for constructor injection

//package io.mastercoding.diapp1
//
//import javax.inject.Inject
//
//class Car @Inject constructor(private val engine: Engine){
//     //dependecy pass from a outside
//    fun drive(){
//        println(engine.start())
//    }
//}