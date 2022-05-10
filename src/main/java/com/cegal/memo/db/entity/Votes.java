package com.cegal.memo.db.entity;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Votes {
    private String id;
    private String cardId;
}
