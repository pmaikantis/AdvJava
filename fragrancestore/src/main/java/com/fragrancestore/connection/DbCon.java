package com.fragrancestore.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fragrancestore", 
                    "root", 
                    "#Penny91" 
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return connection;
    }

    public static void main(String[] args) {
    
        Connection con = DbCon.getConnection();
        if (con != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Database connection failed!");
        }
    }
}
