package com.cegal.memo.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteResponse {
    private String cardId;
    private int count;
}
