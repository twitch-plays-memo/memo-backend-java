package com.cegal.memo.db.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBClient {

    private static final String CONNECTION_URL = 
        "jdbc:sqlserver://twitch-plays.database.windows.net:1433;database=memo;user=memo@twitch-plays;password=Plays_987123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }
}
