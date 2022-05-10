package com.cegal.memo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.cegal.memo.db.repo.*;
import com.cegal.memo.db.entity.GameState;


public class GameStateRepoTest {
    @Test
    public void testRead() throws Exception {
        GameState state = new GameStateRepo().read();
        System.out.println("*** STATE: " + state.getState());
    }
    @Test
    public void testWrite() throws Exception {
        GameState state = GameState.builder()
            .id("the-game")
            .state("round-cooldown")
            .build();
        new GameStateRepo().write(state);
    }

        
}
