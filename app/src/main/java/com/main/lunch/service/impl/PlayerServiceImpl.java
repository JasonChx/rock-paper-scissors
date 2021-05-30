package com.main.lunch.service.impl;

import com.main.lunch.entity.Player;
import com.main.lunch.repository.PlayerRepo;
import com.main.lunch.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Override
    public List<String> getNameList() {
        return playerRepo.findAllName();
    }

    @Override
    public boolean existsByName(String name){ return playerRepo.existsByName(name); }

    @Override
    public Player findByName(String name) { return playerRepo.findByName(name); }
}
