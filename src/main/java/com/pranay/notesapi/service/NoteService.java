package com.pranay.notesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pranay.notesapi.dto.NoteRequest;
import com.pranay.notesapi.dto.NoteResponse;
import com.pranay.notesapi.model.Note;
import com.pranay.notesapi.repository.NoteRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Page<NoteResponse> getAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public Optional<NoteResponse> getNoteById(Long id) {
        return noteRepository.findById(id)
                .map(this::mapToResponse);
    }

    public NoteResponse createNote(NoteRequest request) {
        Note note = new Note(request.getTitle(), request.getContent());
        Note savedNote = noteRepository.save(note);

        return mapToResponse(savedNote);
    }

    public Optional<NoteResponse> updateNote(Long id, NoteRequest request) {
        return noteRepository.findById(id).map(existingNote -> {
            existingNote.setTitle(request.getTitle());
            existingNote.setContent(request.getContent());

            Note savedNote = noteRepository.save(existingNote);
            return mapToResponse(savedNote);
        });
    }

    public boolean deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            return false;
        }

        noteRepository.deleteById(id);
        return true;
    }

    public List<NoteResponse> searchNotesByTitle(String title) {
        return noteRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private NoteResponse mapToResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
