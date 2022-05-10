package com.cegal.memo.db.entity;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class GameState {
    private String id;
    private String state;    
}
