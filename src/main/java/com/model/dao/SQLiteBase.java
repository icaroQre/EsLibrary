package com.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteBase {
    protected Connection conn;
    
    public Connection open () {

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close () {
        try {
        if(conn != null) 
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}