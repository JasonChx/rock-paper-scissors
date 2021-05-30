package com.main.lunch.repository;

import com.main.lunch.entity.Player;
import com.main.lunch.entity.types.OutcomeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecordRepoTest {

    @Autowired
    private RecordRepo recordRepo;

    @Autowired
    private PlayerRepo playerRepo;

    @Test
    public void testCountByPlayer_findAllSix() {
        Player player = playerRepo.findByName("John");
        long size = recordRepo.countByPlayer(player);
        assertThat( size ).as( "Test fail for method findAllName, incorrect number found, expected: 6, actually found: "+size ).isEqualTo(6);
    }

    @Test
    public void testCountByPlayerAndOutcome_findCorrectNumber() {
        Player player = playerRepo.findByName("John");
        long size = recordRepo.countByPlayerAndOutcome(player, OutcomeType.DRAW);
        assertThat( size ).as( "Test fail for method countByPlayerAndOutcome, incorrect number found, expected: 1, actually found: "+size ).isEqualTo(1);
        size = recordRepo.countByPlayerAndOutcome(player, OutcomeType.WIN);
        assertThat( size ).as( "Test fail for method countByPlayerAndOutcome, incorrect number found, expected: 2, actually found: "+size ).isEqualTo(2);
        size = recordRepo.countByPlayerAndOutcome(player, OutcomeType.LOSS);
        assertThat( size ).as( "Test fail for method countByPlayerAndOutcome, incorrect number found, expected: 3, actually found: "+size ).isEqualTo(3);
    }
}
