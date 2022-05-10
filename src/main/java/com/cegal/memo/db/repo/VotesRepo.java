package com.cegal.memo.db.repo;

import com.cegal.memo.db.client.DBClient;
import com.cegal.memo.db.entity.Votes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class VotesRepo {

    public List<Votes> read() {
        ArrayList<Votes> voteList = new ArrayList<>();       

        try (Connection connection = DBClient.getConnection();
            Statement statement = connection.createStatement();) {
            String sql = "SELECT * FROM [dbo].[VOTES]";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Votes votes = Votes.builder()
                    .id(resultSet.getString("id"))
                    .cardId(resultSet.getString("card_id"))
                    .build();
                voteList.add(votes);
            }
        } catch (SQLException e) {
            System.out.println("*** Error in reading from GAME_STATS");
            e.printStackTrace();
        }

        return voteList;
    }       
}
