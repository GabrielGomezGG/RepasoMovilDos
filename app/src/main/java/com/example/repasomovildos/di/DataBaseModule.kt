package com.example.repasomovildos.di

import android.content.Context
import androidx.room.Room
import com.example.repasomovildos.data.PostDBRepository
import com.example.repasomovildos.data.PostDBRepositoryImpl
import com.example.repasomovildos.data.PostDBRepositoryImpl_Factory
import com.example.repasomovildos.data.PostRepository
import com.example.repasomovildos.data.database.PostDao
import com.example.repasomovildos.data.database.PostDataBase
import com.example.repasomovildos.domain.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun roomProvide(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            PostDataBase::class.java,
            "app_database"
        ).build()


    @Singleton
    @Provides
    fun postDaoProvide(appDataBase: PostDataBase): PostDao {
        return appDataBase.postDao()
    }

    @Singleton
    @Provides
    fun providePostDBRepository(postDao: PostDao) : PostDBRepository {
        return PostDBRepositoryImpl(postDao)
    }

    @Singleton
    @Provides
    fun provideGetPostUseCase(postDBRepository: PostDBRepository, postRepository: PostRepository) : GetPostUseCase {
        return GetPostUseCase(postRepository, postDBRepository)
    }

}