package com.cegal.memo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import com.cegal.memo.db.repo.*;
import com.cegal.memo.db.entity.GameStats;


public class DBClientTest {
    @Test
    public void testRead() throws Exception {
        GameStats stats = new GameStatsRepo().read();
        System.out.println("*** SCORE: " + stats.getScore());
    }
    @Test
    public void testWrite() throws Exception {
        GameStats stats = GameStats.builder()
            .id("the-game")
            .score(222)
            .time(15)
            .turns(8)
            .totalCards(10)
            .activeCardIndexes(Arrays.asList("9,8,7".split("\\s*,\\s*")))
            .build();
        new GameStatsRepo().write(stats);
    }
}
