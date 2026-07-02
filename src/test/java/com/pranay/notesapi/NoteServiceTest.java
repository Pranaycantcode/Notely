package com.pranay.notesapi;

import com.pranay.notesapi.dto.NoteRequest;
import com.pranay.notesapi.dto.NoteResponse;
import com.pranay.notesapi.model.Note;
import com.pranay.notesapi.repository.NoteRepository;
import com.pranay.notesapi.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    private final NoteRepository noteRepository = Mockito.mock(NoteRepository.class);
    private final NoteService noteService = new NoteService(noteRepository);

    @Test
    void shouldCreateNote() {
        NoteRequest request = new NoteRequest("Test title", "Test content");
        Note savedNote = new Note("Test title", "Test content");

        Mockito.when(noteRepository.save(Mockito.any(Note.class))).thenReturn(savedNote);

        NoteResponse response = noteService.createNote(request);

        assertEquals("Test title", response.getTitle());
        assertEquals("Test content", response.getContent());
    }
}