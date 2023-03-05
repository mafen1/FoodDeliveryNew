package com.example.fooddelivery.di

import android.content.Context
import androidx.room.Room
import com.example.fooddelivery.data.homeScreen.api.ApiService
import com.example.fooddelivery.data.homeScreen.repository.RepositoryImpl
import com.example.fooddelivery.data.login.LoginDatabase
import com.example.fooddelivery.domain.repository.homeScreen.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLoginDatabase(@ApplicationContext context: Context): LoginDatabase =
        Room.databaseBuilder(context, LoginDatabase::class.java, "app_database")
            .build()

    @Provides
    @Singleton
    fun provideNoteDao(appDatabase: LoginDatabase) = appDatabase.createAccountDao()


}
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleOP{
    @Binds
    abstract fun bindsRepository(repository: RepositoryImpl) : HomeRepository

    @Binds
    abstract fun bindsApiService(apiService: ApiService.ApiServiceImpl) : ApiService
}