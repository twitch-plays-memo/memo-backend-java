package com.cegal.memo.db.repo;

import com.cegal.memo.db.client.DBClient;
import com.cegal.memo.db.entity.GameStats;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.sql.ResultSet;

public class GameStatsRepo {

    private static final String GAME_ID = "the-game";
    
    public GameStats read() {
        GameStats stats = new GameStats();       
        ResultSet resultSet = null;

        try (Connection connection = DBClient.getConnection();
            Statement statement = connection.createStatement();) {
            String selectSql = "SELECT * FROM [dbo].[GAME_STATS] where id='" + GAME_ID + "'";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            if (resultSet.next()) {
                stats.setId(GAME_ID);
                stats.setScore(resultSet.getInt("score"));
                stats.setTime(resultSet.getInt("time"));
                stats.setTurns(resultSet.getInt("turns"));
                stats.setTotalCards(resultSet.getInt("total_cards"));
                String indexesText = resultSet.getString("turns");
                List<String> indexes = Arrays.asList(indexesText.split("\\s*,\\s*"));
                stats.setActiveCardIndexes(indexes);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

        return stats;
    }        
}
