package com.pranay.notesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pranay.notesapi.dto.NoteRequest;
import com.pranay.notesapi.dto.NoteResponse;
import com.pranay.notesapi.model.Note;
import com.pranay.notesapi.repository.NoteRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Page<NoteResponse> getAllNotes(Pageable pageable) {
        logger.info("Fetching notes. Page: {}, Size: {}",
                pageable.getPageNumber(),
                pageable.getPageSize());

        return noteRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public Optional<NoteResponse> getNoteById(Long id) {
        logger.info("Fetching note with id: {}", id);

        Optional<NoteResponse> note = noteRepository.findById(id)
                .map(this::mapToResponse);

        if (note.isEmpty()) {
            logger.warn("Note not found with id: {}", id);
        }

        return note;
    }

    public NoteResponse createNote(NoteRequest request) {
        logger.info("Creating note with title: {}", request.getTitle());

        Note note = new Note(request.getTitle(), request.getContent());
        Note savedNote = noteRepository.save(note);

        logger.info("Note created successfully with id: {}", savedNote.getId());

        return mapToResponse(savedNote);
    }

    public Optional<NoteResponse> updateNote(Long id, NoteRequest request) {
        logger.info("Updating note with id: {}", id);

        Optional<Note> existingNote = noteRepository.findById(id);

        if (existingNote.isEmpty()) {
            logger.warn("Update failed. Note not found with id: {}", id);
            return Optional.empty();
        }

        Note note = existingNote.get();

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        Note savedNote = noteRepository.save(note);

        logger.info("Note updated successfully with id: {}", savedNote.getId());

        return Optional.of(mapToResponse(savedNote));
    }

    public boolean deleteNote(Long id) {
        logger.info("Deleting note with id: {}", id);

        if (!noteRepository.existsById(id)) {
            logger.warn("Delete failed. Note not found with id: {}", id);
            return false;
        }

        noteRepository.deleteById(id);
        logger.info("Note deleted successfully with id: {}", id);

        return true;
    }

    public List<NoteResponse> searchNotesByTitle(String title) {
        logger.info("Searching notes by title: {}", title);

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
                note.getUpdatedAt());
    }

    public List<NoteResponse> searchNotesByKeyword(String keyword) {
        logger.info("Searching notes with keyword: {}", keyword);

        return noteRepository.searchByTitleOrContent(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}
