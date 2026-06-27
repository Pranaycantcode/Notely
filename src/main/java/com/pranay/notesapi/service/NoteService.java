package com.pranay.notesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pranay.notesapi.model.Note;
import com.pranay.notesapi.repository.NoteRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Optional<Note> updateNote(Long id, Note updatedNote) {
        return noteRepository.findById(id).map(existingNote -> {
            existingNote.setTitle(updatedNote.getTitle());
            existingNote.setContent(updatedNote.getContent());
            return noteRepository.save(existingNote);
        });
    }

    public boolean deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            return false;
        }

        noteRepository.deleteById(id);
        return true;
    }

    public List<Note> searchNotesByTitle(String title) {
        return noteRepository.findByTitleContainingIgnoreCase(title);
    }
}
