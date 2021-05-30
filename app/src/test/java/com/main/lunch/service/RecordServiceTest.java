package com.main.lunch.service;

import com.main.lunch.entity.Player;
import com.main.lunch.entity.types.OutcomeType;
import com.main.lunch.repository.RecordRepo;
import com.main.lunch.service.impl.RecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecordServiceTest {

    @Mock
    private RecordRepo recordRepo;

    @InjectMocks
    private RecordServiceImpl recordService;

    @Test
    public void testGetStatisticsForPlayer() {
        Player player = new Player(1, "John");

        when(recordRepo.countByPlayer(player)).thenReturn(6L);

        when(recordRepo.countByPlayerAndOutcome(player, OutcomeType.DRAW)).thenReturn(1L);

        when(recordRepo.countByPlayerAndOutcome(player, OutcomeType.WIN)).thenReturn(2L);

        when(recordRepo.countByPlayerAndOutcome(player, OutcomeType.LOSS)).thenReturn(3L);

        assertThat(recordService.getStatisticsForPlayer(player)).as("Test fail for method getStatisticsForPlayer, incorrect message displayed").isEqualTo("Player 'John' played 6 round(s), wins: 2 losses: 3 and draws: 1");
    }
}
