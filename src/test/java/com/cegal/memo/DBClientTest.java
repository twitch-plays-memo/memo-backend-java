package com.cegal.memo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.cegal.memo.db.repo.*;


public class DBClientTest {
    @Test
    public void testReader() throws Exception {
        new TestRepo().reader();
    }
}
