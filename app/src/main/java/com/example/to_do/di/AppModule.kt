package com.example.to_do.di

import android.content.Context
import com.example.to_do.database.TaskDatabase
import com.example.to_do.database.TaskDao
import com.example.to_do.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTaskDao(@ApplicationContext appContext: Context): TaskDao {
        return TaskDatabase.getDatabase(appContext).taskDao()
    }

    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao)
    }
}
