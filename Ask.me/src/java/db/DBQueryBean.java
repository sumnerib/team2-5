/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.*;
import java.sql.*;

/**
 * This is a bean that uses the members table
 * 
 * @author Isaac Sumner
 * @version 11.23.16
 */
public class DBQueryBean {
    
    
    private String 
            driverClassName = "grove.cs.jmu.edu",
            dbUrl = "jdbc:mysql://localhost:3306/team25_db",
            dbUserId = "team25",
            dbPassword = "k!lo6";
    private Connection con;
    
    /**
     * This constructs the bean and opens its connection.
     */
    public DBQueryBean() {
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(
                    dbUrl, dbUserId, dbPassword);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This populates a QueryResult with the data returned from the given query
     * 
     * @param query The SQL query
     * @return The result of the query
     */
    public ResultSet doQuery(String query) {
        
        QueryResult result = new QueryResult();
        
        try {
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query); 
            /**
            ResultSetMetaData md = rs.getMetaData();
            
            // Populate the result
            for (int i = 0; i < md.getColumnCount(); i++)
                result.addColumnName(md.getColumnName(i));
            
            while (rs.next()) {
                
                for (int i = 0; i < md.getColumnCount(); i++)
                    result.addFieldValue(rs.getString(i));
            }
            */
            
            return rs;
              
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
    
    /**
     * This closes the database connection
     * 
     * @throws SQLException 
     */
    public void closeCon() throws SQLException {
        con.close();
    }
}

