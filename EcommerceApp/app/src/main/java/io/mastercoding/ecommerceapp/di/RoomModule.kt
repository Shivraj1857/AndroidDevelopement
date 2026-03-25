package io.mastercoding.ecommerceapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.mastercoding.ecommerceapp.room.CartDao
import io.mastercoding.ecommerceapp.room.CartDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context):CartDatabase{
        return CartDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDAO(database: CartDatabase): CartDao{
        return database.cartDao()
    }



}