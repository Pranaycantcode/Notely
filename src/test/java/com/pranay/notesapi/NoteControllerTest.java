package com.pranay.notesapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranay.notesapi.dto.NoteRequest;
import com.pranay.notesapi.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private NoteService noteService;

    @Test
    void shouldRejectInvalidNoteRequest() throws Exception {
        NoteRequest request = new NoteRequest("", "");

        mockMvc.perform(post("/notes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}