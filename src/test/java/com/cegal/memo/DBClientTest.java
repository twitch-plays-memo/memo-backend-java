package com.cegal.memo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.cegal.memo.db.repo.*;
import com.cegal.memo.db.entity.GameStats;


public class DBClientTest {
    @Test
    public void testReader() throws Exception {
        GameStats stats = new GameStatsRepo().read();
        System.out.println("*** SCORE: " + stats.getScore());
    }
}
