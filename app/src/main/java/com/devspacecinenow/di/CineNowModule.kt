package com.devspacecinenow.di

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.common.data.local.CineNowDataBase
import com.devspacecinenow.common.data.local.MovieDao
import com.devspacecinenow.common.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CineNowModule {

    @Provides
    fun providesCineNowDatabase(application: Application): CineNowDataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            CineNowDataBase::class.java, "database-cine-now"
        ).build()
    }

    @Provides
    fun providesMovieDao(roomDatabase: CineNowDataBase): MovieDao {
        return roomDatabase.getMovieDao()
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return RetrofitClient.retrofitInstance
    }

    @Provides
    @DispatcherIo
    fun providesDispatcherIo(): CoroutineDispatcher{
        return Dispatchers.IO
    }

}