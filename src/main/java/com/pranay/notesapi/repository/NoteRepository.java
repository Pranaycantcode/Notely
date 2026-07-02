package com.pranay.notesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.pranay.notesapi.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT n FROM Note n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Note> searchByTitleOrContent(@Param("keyword") String keyword);
}