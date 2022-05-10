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
        GameStats stats = null;       
        ResultSet resultSet = null;

        try (Connection connection = DBClient.getConnection();
            Statement statement = connection.createStatement();) {
            String sql = "SELECT * FROM [dbo].[GAME_STATS] where id='" + GAME_ID + "'";
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String indexesText = resultSet.getString("active_card_indexes");
                List<String> indexes = Arrays.asList(indexesText.split("\\s*,\\s*"));
                stats = GameStats.builder()
                    .id(GAME_ID)
                    .score(resultSet.getInt("score"))
                    .time(resultSet.getInt("time"))
                    .turns(resultSet.getInt("turns"))
                    .totalCards(resultSet.getInt("total_cards"))
                    .activeCardIndexes(indexes)
                    .build();
            }
        } catch (SQLException e) {
            System.out.println("*** Error in reading from GAME_STATS");
            e.printStackTrace();
        }

        return stats;
    }       
    
    public void write(GameStats stats) {
        try (Connection connection = DBClient.getConnection();
            Statement statement = connection.createStatement();) {
            String sql = "UPDATE [dbo].[GAME_STATS] SET "
                + "score=" + stats.getScore() + ","
                + "time=" + stats.getTime() + ","
                + "turns=" + stats.getTurns() + ","
                + "total_cards=" + stats.getTotalCards() + ","
                + "active_card_indexes='" +  String.join(",", stats.getActiveCardIndexes()) + "'"
                + "WHERE id='" + GAME_ID + "'";
            statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("*** Error in writing into GAME_STATS");
            e.printStackTrace();
        }
    }
}
