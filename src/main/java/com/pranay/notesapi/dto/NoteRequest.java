package com.pranay.notesapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(max = 1000, message = "Content cannot exceed 1000 characters")
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}