package com.main.lunch.controller;

import com.main.lunch.entity.Record;
import com.main.lunch.entity.types.OutcomeType;
import com.main.lunch.entity.types.ShapeType;
import com.main.lunch.service.PlayerService;
import com.main.lunch.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private RecordService recordService;

    @GetMapping(path="/play", produces = "application/json")
    public String play(String player, String shape){
        if(!playerService.existsByName(player)){
            return "Error: Player '" + player + "' does not exist!";
        } else if(!ShapeType.exist(shape)){
            return "Error: Shape '" + shape + "' does not exist! Please select from 'Rock','Paper' and 'Scissors' only";
        } else {
            ShapeType applicationShape = ShapeType.randomShape();
            OutcomeType outcome = getOutcome(ShapeType.get(shape), applicationShape);
            recordService.save(new Record(playerService.findByName(player), outcome, LocalDateTime.now()));
            return "Game played success: Player " + player + " thrown '" + ShapeType.get(shape).getName() +
                    "' and application thrown '" + applicationShape.getName() + "'\nThe outcome is " + outcome.name();
        }
    }

    private OutcomeType getOutcome(ShapeType playerShape, ShapeType applicationShape){
        if(playerShape.equals(applicationShape)){
            return OutcomeType.DRAW;
        } else if(playerShape.getValue() - applicationShape.getValue() == 1
                || applicationShape.getValue() - playerShape.getValue() == 2){
            return OutcomeType.WIN;
        }
        return OutcomeType.LOSS;
    }

}
