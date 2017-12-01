/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teradata.gdc.manila.rdg.util;

/**
 * @author jl186034
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class TeraConnection {

    private static String jdbcUrl;
    private static String username;
    private static String password;
    private static Connection cn;

    public TeraConnection(String jdbcUrl, String username, String password) throws ClassNotFoundException, SQLException {
        TeraConnection.jdbcUrl = jdbcUrl;
        TeraConnection.username = username;
        TeraConnection.password = password;
        TeraConnection.connect();
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.teradata.jdbc.TeraDriver");
        //System.out.println(" JDBC driver loaded. \n");
        TeraConnection.cn = DriverManager.getConnection(TeraConnection.jdbcUrl, TeraConnection.username, TeraConnection.password);
        //System.out.println(TeraConnection.cn.getClientInfo());
        //System.out.println(" User " + TeraConnection.username + " connected.");
        //System.out.println(" Connection to Teradata established. \n");
    }

// --Commented out by Inspection START (11/27/2017 9:35 AM):
//    public static void setJdbcUrl(String jdbcUrl) {
//        TeraConnection.jdbcUrl = jdbcUrl;
//    }
// --Commented out by Inspection STOP (11/27/2017 9:35 AM)

// --Commented out by Inspection START (11/27/2017 9:35 AM):
//    public static void setUsername(String username) {
//        TeraConnection.username = username;
//    }
// --Commented out by Inspection STOP (11/27/2017 9:35 AM)

// --Commented out by Inspection START (11/27/2017 9:36 AM):
//    public static void setPassword(String password) {
//        TeraConnection.password = password;
//    }
// --Commented out by Inspection STOP (11/27/2017 9:36 AM)

    public void closeConnection() throws SQLException {
        TeraConnection.cn.close();
    }

    public Connection getConnection() {
        return TeraConnection.cn;
    }
}
