package com.task.noteapp.di

import android.content.Context
import androidx.room.Room
import com.task.noteapp.data.local.NoteDao
import com.task.noteapp.data.local.NoteDatabase
import com.task.noteapp.data.repository.NoteRepository
import com.task.noteapp.data.repository.NoteRepositoryImp
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
    fun providesRepository(
        noteDao: NoteDao
    ): NoteRepository {
        return NoteRepositoryImp(noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            "notedatabase"
        ).build()
    }

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }
}