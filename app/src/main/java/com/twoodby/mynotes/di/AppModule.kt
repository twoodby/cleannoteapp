package com.twoodby.mynotes.di

import android.app.Application
import androidx.room.Room
import com.twoodby.mynotes.feature_note.data.data_source.NoteDatabase
import com.twoodby.mynotes.feature_note.data.repository.NoteRepositoryImpl
import com.twoodby.mynotes.feature_note.domain.repository.NoteRepository
import com.twoodby.mynotes.feature_note.domain.use_case.DeleteNoteUseCase
import com.twoodby.mynotes.feature_note.domain.use_case.GetNotesUseCase
import com.twoodby.mynotes.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME).build()
    }


    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository = repository),
            deleteNote = DeleteNoteUseCase(repository = repository)
        )
    }
}
