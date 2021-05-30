package com.main.lunch.controller;

import com.main.lunch.service.PlayerService;
import com.main.lunch.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private RecordService recordService;

    @GetMapping(path = "/getList", produces = "application/json")
    public List<String> getList() {
        return playerService.getNameList();
    }

    @GetMapping(path = "/getStatistics", produces = "application/json")
    public String getStatistics(String player) {
        if (!playerService.existsByName(player)) {
            return "Error: Player '" + player + "' does not exist!";
        } else {
            return recordService.getStatisticsForPlayer(playerService.findByName(player));
        }
    }
}
