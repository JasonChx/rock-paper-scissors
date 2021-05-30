package com.main.lunch.controller;

import com.main.lunch.service.PlayerService;
import com.main.lunch.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
public class GameControllerTest {
    @MockBean
    private PlayerService playerService;

    @MockBean
    private RecordService recordService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testPlay_PlayerNotExist() throws Exception {
        when(playerService.existsByName("test")).thenReturn(false);

        mvc.perform(MockMvcRequestBuilders.get("/game/play").param("player", "test").param("shape", "rock"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Error: Player 'test' does not exist!"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testPlay_ShapeNotExist() throws Exception {
        when(playerService.existsByName("John")).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.get("/game/play").param("player", "John").param("shape", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Error: Shape 'test' does not exist! Please select from 'Rock','Paper' and 'Scissors' only"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testPlay_Success() throws Exception {
        when(playerService.existsByName("John")).thenReturn(true);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/game/play").param("player", "John").param("shape", "rock"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).as("Test fail for API game/play").matches("^Game played success: Player John thrown 'rock' and application thrown '(rock|paper|scissors)'\nThe outcome is (DRAW|LOSS|WIN)$");
    }
}
