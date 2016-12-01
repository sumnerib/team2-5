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
public class DBAnswerBean {
    
    
    private String 
            driverClassName = "grove.cs.jmu.edu",
            dbUrl = "jdbc:mysql://localhost:3306/team25_db",
            dbUserId = "team25",
            dbPassword = "k!lo6";
    
    public QueryResult doQuery() {
        
        QueryResult result = new QueryResult();
        
        try {
            Class.forName(driverClassName);
            Connection con = DriverManager.getConnection(
                    dbUrl, dbUserId, dbPassword);
            
            Statement st = con.createStatement();
//            ResultSet rs =          
//                    st.executeQuery("");       
//            ResultSetMetaData md = rs.getMetaData();
              /*
              Populate the query result  
              */
              
              con.close();
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return result;
    }
}

