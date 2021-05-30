package com.main.lunch.repository;

import com.main.lunch.entity.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerRepoTest {

    @Autowired
    private PlayerRepo playerRepo;

    @Test
    public void testFindAllName_FindAllFour() {
        int size = playerRepo.findAllName().size();
        assertThat(size).as("Test fail for method findAllName, incorrect number found, expected: 4, actually found: " + size).isEqualTo(4);
    }

    @Test
    public void testFindByName_FindCorrectPlayer() {
        Player player = playerRepo.findByName("John");
        assertThat(player).as("Test fail for method findByName, found null").isNotNull();
        assertThat(player.getName()).as("Test fail for method findByName, found wrong player").isEqualTo("John");
    }

    @Test
    public void testExistsByName_Exist() {
        assertThat(playerRepo.existsByName("John")).as("Test fail for method existsByName").isTrue();
    }

    @Test
    public void testExistsByName_NotExist() {
        assertThat(playerRepo.existsByName("Jack")).as("Test fail for method existsByName").isFalse();
    }
}
