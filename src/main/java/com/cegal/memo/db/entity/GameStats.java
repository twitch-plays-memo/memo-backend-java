package com.cegal.memo.db.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class GameStats {
    private String id;
    private int score;
    private int time;
    private int turns;
    private int totalCards;
    private List<String> activeCardIndexes;
}
