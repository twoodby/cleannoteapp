package com.twoodby.mynotes.feature_note.domain.use_case

import com.twoodby.mynotes.feature_note.domain.model.InvalidNoteException
import com.twoodby.mynotes.feature_note.domain.model.Note
import com.twoodby.mynotes.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("the title of the note can not be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("the content of the not can not be empty")
        }
        repository.insertNote(note = note)
    }
}
