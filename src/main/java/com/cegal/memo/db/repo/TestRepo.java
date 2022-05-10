package com.cegal.memo.db.repo;

import com.cegal.memo.db.client.DBClient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class TestRepo {
    
    public String reader() {

        String ret = "default";       
        ResultSet resultSet = null;

        try (Connection connection = DBClient.getConnection();
            Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM [dbo].[test]";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                ret = resultSet.getString(2);
                System.out.println("value: " +  ret);

            }
    }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }        
}
