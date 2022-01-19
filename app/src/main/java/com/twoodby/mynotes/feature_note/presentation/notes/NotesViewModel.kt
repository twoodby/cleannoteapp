package com.twoodby.mynotes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twoodby.mynotes.feature_note.domain.model.Note
import com.twoodby.mynotes.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NoteStates())
    val state: State<NoteStates> = _state

    private var recentlyDeletedNote: Note? = null

    fun onEvent(event: NotesEvents) {
        when (event) {
            is NotesEvents.Order -> {
            }
            is NotesEvents.DeleteNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote = event.note
                    noteUseCases.deleteNote(event.note)
                }
            }
            is NotesEvents.RestoreNote -> {
                viewModelScope.launch {

                }

            }
            is NotesEvents.ToggleOrderSelection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }


}
