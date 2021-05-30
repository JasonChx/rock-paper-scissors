package com.main.lunch.service.impl;

import com.main.lunch.entity.Player;
import com.main.lunch.entity.Record;
import com.main.lunch.entity.types.OutcomeType;
import com.main.lunch.repository.RecordRepo;
import com.main.lunch.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepo recordRepo;

    @Override
    public String getStatisticsForPlayer(Player player) {
        long rounds = recordRepo.countByPlayer(player);
        long wins = recordRepo.countByPlayerAndOutcome(player, OutcomeType.WIN);
        long losses = recordRepo.countByPlayerAndOutcome(player, OutcomeType.LOSS);
        long draws = recordRepo.countByPlayerAndOutcome(player, OutcomeType.DRAW);
        return "Player '" + player.getName() + "' played " + rounds + " round(s), " + "wins: " + wins + " losses: " + losses + " and draws: " + draws;
    }

    @Override
    public void save(Record record) {
        recordRepo.save(record);
    }
}
