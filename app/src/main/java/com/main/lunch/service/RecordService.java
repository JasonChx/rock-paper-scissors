package com.main.lunch.service;

import com.main.lunch.entity.Player;
import com.main.lunch.entity.Record;

public interface RecordService {

    public String getStatisticsForPlayer(Player player);

    public void save(Record record);
}
