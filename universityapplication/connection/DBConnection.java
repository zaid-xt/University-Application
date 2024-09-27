/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universityapplication.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public static Connection createConnection() throws SQLException {
        String URL = "jdbc:derby://localhost:1527/University";
        String USERNAME = "Administrator";
        String PASSWORD = "Admin";
        System.out.println("About to get a connection....");
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connection Established Successfully....");

        System.out.println("Creating statement Object....");
        Statement stmt = con.createStatement();
        System.out.println("Statement object created Successfully....");

        return con;
    }
}
