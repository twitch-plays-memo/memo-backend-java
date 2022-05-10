package com.cegal.memo.db.repo;

import com.cegal.memo.db.client.DBClient;
import com.cegal.memo.db.entity.GameState;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class GameStateRepo {
    private static final String GAME_ID = "the-game";
    
    public GameState read() {
        GameState state = null;       
        ResultSet resultSet = null;

        try (Connection connection = DBClient.getConnection();
            Statement statement = connection.createStatement();) {
            String sql = "SELECT * FROM [dbo].[GAME_STATE] where id='" + GAME_ID + "'";
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                state = GameState.builder()
                    .id(GAME_ID)
                    .state(resultSet.getString("state"))
                    .build();
            }
        } catch (SQLException e) {
            System.out.println("*** Error in reading from GAME_STATE");
            e.printStackTrace();
        }

        return state;
    }       
    
    public void write(GameState state) {
        try (Connection connection = DBClient.getConnection();
            Statement statement = connection.createStatement();) {
            String sql = "UPDATE [dbo].[GAME_STATE] SET "
                + "state='" + state.getState() + "'"
                + "WHERE id='" + GAME_ID + "'";
            statement.executeQuery(sql);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("*** Error in writing into GAME_STATE");
            e.printStackTrace();
        }
    }

}
