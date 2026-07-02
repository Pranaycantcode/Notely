package com.pranay.notesapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pranay.notesapi.dto.ApiResponse;
import com.pranay.notesapi.dto.NoteRequest;
import com.pranay.notesapi.dto.NoteResponse;
import com.pranay.notesapi.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ApiResponse<Page<NoteResponse>> getAllNotes(Pageable pageable) {
        Page<NoteResponse> notes = noteService.getAllNotes(pageable);

        return new ApiResponse<>(
                true,
                "Notes fetched successfully",
                notes
        );
    }

    @GetMapping("/search")
    public ApiResponse<List<NoteResponse>> searchNotes(@RequestParam String title) {
        List<NoteResponse> notes = noteService.searchNotesByTitle(title);

        return new ApiResponse<>(
                true,
                "Notes search completed successfully",
                notes
        );
    }

    @GetMapping("/keyword-search")
    public ApiResponse<List<NoteResponse>> searchNotesByKeyword(@RequestParam String keyword) {
        List<NoteResponse> notes = noteService.searchNotesByKeyword(keyword);

        return new ApiResponse<>(
                true,
                "Keyword search completed successfully",
                notes
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteResponse>> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(note -> ResponseEntity.ok(
                        new ApiResponse<>(
                                true,
                                "Note fetched successfully",
                                note
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ApiResponse<NoteResponse> createNote(@Valid @RequestBody NoteRequest request) {
        NoteResponse note = noteService.createNote(request);

        return new ApiResponse<>(
                true,
                "Note created successfully",
                note
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteResponse>> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request
    ) {
        return noteService.updateNote(id, request)
                .map(note -> ResponseEntity.ok(
                        new ApiResponse<>(
                                true,
                                "Note updated successfully",
                                note
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNote(@PathVariable Long id) {
        boolean deleted = noteService.deleteNote(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Note deleted successfully",
                        null
                )
        );
    }
}