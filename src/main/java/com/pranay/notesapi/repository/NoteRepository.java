package com.pranay.notesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranay.notesapi.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByTitleContainingIgnoreCase(String title);
}