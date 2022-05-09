package com.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBClient {
    
    public static void main(String[] args) {
        new DBClient().reader();
    }


    public String reader() {

        String ret = "default";

        String connectionUrl = 
        "jdbc:sqlserver://twitch-plays.database.windows.net:1433;database=memo;user=memo@twitch-plays;password=Plays_987123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
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
