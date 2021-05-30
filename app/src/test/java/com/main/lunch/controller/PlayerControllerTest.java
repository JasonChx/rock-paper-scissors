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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @MockBean
    private PlayerService playerService;

    @MockBean
    private RecordService recordService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testGetList_FindAll() throws Exception {
        List<String> playerList = new ArrayList<>();
        playerList.add("John");
        playerList.add("Andrew");
        playerList.add("Jason");
        playerList.add("Bill");

        when(playerService.getNameList()).thenReturn(playerList);

        mvc.perform(MockMvcRequestBuilders.get("/player/getList"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$", hasItems("John", "Andrew", "Jason", "Bill")));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testGetStatistics_GetCorrectResultList() throws Exception {

        when(playerService.existsByName("John")).thenReturn(true);

        when(recordService.getStatisticsForPlayer(playerService.findByName("John"))).thenReturn("Player 'John' played 6 round(s), wins: 2 losses: 3 and draws: 1");

        mvc.perform(MockMvcRequestBuilders.get("/player/getStatistics").param("player", "John"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Player 'John' played 6 round(s), wins: 2 losses: 3 and draws: 1"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testGetStatistics_PlayerNotExist() throws Exception {

        when(playerService.existsByName("test")).thenReturn(false);

        mvc.perform(MockMvcRequestBuilders.get("/player/getStatistics").param("player", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Error: Player 'test' does not exist!"));
    }
}
