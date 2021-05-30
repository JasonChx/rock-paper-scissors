package com.main.lunch.repository;

import com.main.lunch.entity.Player;
import com.main.lunch.entity.Record;
import com.main.lunch.entity.types.OutcomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepo extends JpaRepository<Record, Long> {

    long countByPlayer(Player player);

    long countByPlayerAndOutcome(Player player, OutcomeType outcome);
}
