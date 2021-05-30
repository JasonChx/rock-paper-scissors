package com.main.lunch.service;


import com.main.lunch.entity.Player;
import com.main.lunch.repository.PlayerRepo;
import com.main.lunch.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerServiceTest {

    @Mock
    private PlayerRepo playerRepo;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void testGetNameList_FindAll() {
        List<String> playerList = new ArrayList<>();
        playerList.add("John");
        playerList.add("Andrew");
        playerList.add("Jason");
        playerList.add("Bill");

        when(playerRepo.findAllName()).thenReturn(playerList);
        int size = playerService.getNameList().size();
        assertThat(size).as("Test fail for method findAllName, incorrect number found, expected: 4, actually found: " + size).isEqualTo(4);
    }

    @Test
    public void testExistsByName_Exist() {
        when(playerRepo.existsByName("John")).thenReturn(true);
        assertThat(playerService.existsByName("John")).as("Test fail for method existsByName").isTrue();
    }

    @Test
    public void testGetNameList_NotExist() {
        when(playerRepo.existsByName("test")).thenReturn(false);
        assertThat(playerService.existsByName("test")).as("Test fail for method existsByName").isFalse();
    }

    @Test
    public void testFindByName_Found() {
        when(playerRepo.findByName("John")).thenReturn(new Player(1, "John"));
        Player player = playerService.findByName("John");
        assertThat(player).as("Test fail for method findByName, found null").isNotNull();
        assertThat(player.getName()).as("Test fail for method findByName, found wrong player").isEqualTo("John");
    }

    @Test
    public void testFindByName_NotFound() {
        when(playerRepo.findByName("test")).thenReturn(null);
        Player player = playerService.findByName("test");
        assertThat(player).as("Test fail for method findByName, found not null").isNull();
    }
}
