package com.cegal.memo.db.entity;

import lombok.Data;

public @Data class GameStats {
    private String id;
    private int score;
    private int time;
    private int turns;
}
