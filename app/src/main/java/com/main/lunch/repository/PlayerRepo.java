package com.main.lunch.repository;

import com.main.lunch.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {

    @Query("SELECT p.name FROM Player p")
    List<String> findAllName();

    Player findByName(String name);

    boolean existsByName(String name);
}
