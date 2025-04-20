package com.medhat.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String url = "jdbc:mysql://localhost:3306/bank";
    static final String user = "root";
    static final String pass = "";

    public static Connection connect() {
        try {
            Connection d = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to database!!!");
            return d;
        } catch (SQLException e) {
            System.out.println("Connection was not established: " + e.getMessage());
            return null;    
        }
    }
}
