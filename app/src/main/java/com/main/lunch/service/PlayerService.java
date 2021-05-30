package com.main.lunch.service;

import com.main.lunch.entity.Player;

import java.util.List;

public interface PlayerService {

    public List<String> getNameList();

    public boolean existsByName(String name);

    public Player findByName(String name);
}
