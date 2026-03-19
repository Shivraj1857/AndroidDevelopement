package io.mastercoding.diapp1

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//hilt module :define how dependency are provide

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides//obj kasa provide karayacha te hilr la sangtay
    @Singleton//purn app madhi ekach instace rahil toch use karayacha
    fun provideEngine(): Engine{
        return Engine()
    }





}


//constructor injection
//package io.mastercoding.diapp1
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
////hilt module :define how dependency are provide
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides//obj kasa provide karayacha te hilr la sangtay
//    @Singleton//purn app madhi ekach instace rahil toch use karayacha
//    fun provideEngine(): Engine{
//        return Engine()
//    }
//
//    @Provides
//    @Singleton
//    fun provideCar(engine: Engine): Car{
//        return Car(engine)
//    }
//
//
//}