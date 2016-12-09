/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;

/**
 * This is a bean that uses the members table
 * 
 * @author Isaac Sumner
 * @version 11.23.16
 */
public class DBQueryBean {
    
    
    private String 
            driverClassName = "com.mysql.jdbc.Driver",
            dbUrl = "jdbc:mysql://grove.cs.jmu.edu:3306/team25_db",
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
    
    public void doUpdate(String command, ArrayList list)
    {
        try {
            
            PreparedStatement st = con.prepareStatement(command);
            
            st.executeUpdate();
              
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addMember(int memid, String name, String user, 
            String dob, String gender, String img, String pass) {
        try {
            String insert = "INSERT INTO members(memberId, name, admin, username, "
                    + "dob, gender, image, pass) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(insert);
            st.setInt(1, memid);
            st.setString(2, name);
            st.setBoolean(3, false);
            st.setString(4, user);
            st.setDate(5, java.sql.Date.valueOf(dob));
            st.setString(6, gender);
            st.setString(7, img);
            st.setString(8, pass);
            
            st.execute();
              
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Adds the question to db
     * 
     * @param qid question id
     * @param question the user's question
     * @param username 
     */
    public void addQuestion(int qid, String question, String username) {
        
        ResultSet result;
        int mid;
        
        try {
            
            String getMemberIdString = "SELECT memberid FROM members WHERE "
                    + "username = " + username;
            result  = doQuery(getMemberIdString);
            
            result.next();
            mid = result.getInt(1);
            
            //Add the question
            String insert = "INSERT INTO questions(questionId, question, memberId) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(insert);
            st.setInt(1, qid);
            st.setString(2, question);
            st.setInt(3, mid);
            
            st.execute();
        }
        catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
    
    /**
     * This method changes the database info for a member
     * 
     * @param map The info to be changed
     */
    public void editMember(HashMap map, String username) throws SQLException{
        
        ArrayList<String> values = new ArrayList<String>();
        StringBuilder sb = new StringBuilder("UPDATE members SET ");
        Iterator it = map.entrySet().iterator();
        
        // Iterate the map of values and build the query string
        while (it.hasNext()) {
            
            Map.Entry pair = (Map.Entry)it.next();
            
            if (pair.getValue() != null && !(((String)pair.getValue()).equals(""))) {
                
                sb.append(pair.getKey() + " = ?, ");
                values.add((String)pair.getValue());
            }
        }
        
        //******NEED TO FIX SO IT USES DATE VALUE*********
        
        String query = sb.substring(0, sb.length() - 2) + " WHERE username = ?";
        PreparedStatement st = con.prepareStatement(query);
        
        // Some values would be ints in sql so we use parseInt()
        for (int i = 0; i < values.size(); i++) {
            
            try {
                st.setInt(i, Integer.parseInt(values.get(i)));
            }
            catch (NumberFormatException nfe) {
                st.setString(i + 1, values.get(i)); 
            }
        }
        
        st.execute();
    }
    
    /**
     * This checks if a username is in the Database
     * 
     * @param username The username to be checked
     * @return true or false if the user exists
     */
    public boolean checkForUsername(String username) {
        
        int count;
        
        try {
            String sql = "SELECT count(*) FROM members WHERE username = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            
            // Get the result from counting the username tuples
            ResultSet result = st.executeQuery();
            result.next();
            count = result.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            count = 0;
        }
        
        return count > 0;
    }
    
    /**
     * This uses the 
     * @param db The Database handler
     * @return True if valid user credentials are provided false otherwise
     */
    public boolean verifyCred(String username, String pass) throws SQLException {
        boolean verified = false;
        String query = "SELECT pass FROM members WHERE username = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, username);
        
        // Get the result from counting the username tuples
        ResultSet result = st.executeQuery();
        result.next();
        
        result.last();
        if (result.getRow() == 0)
            return false;
        
        result.first();
        if (result.getString(1).trim().equals(pass.trim()))
            verified = true;
        
        return verified;
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

