/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import db.DBQueryBean;
//import db.QueryResult;
import java.io.*;
import java.sql.*;

/**
 *
 * @author trippkm
 */
public class LoginAction 
{
    private String username;
    private String passwd;
    
    
    
    public String getPasswd()
    {
        return passwd;
    }
    
    public void setPsswd(String passwd)
    {
        this.passwd = passwd;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * This uses the 
     * @param db The Database handler
     * @return True if valid user credentials are provided false otherwise
     */
    private boolean verifyCred(DBQueryBean db) throws SQLException
    {
        boolean verified = false;
        String query = "SELECT pass FROM members WHERE username = '" + username
                + "'";
        ResultSet result = db.doQuery(query);
        
        result.last();
        if (result.getRow() == 0)
            return false;
        
        result.first();
        if (result.getString("pass").equals(passwd))
            verified = true;
        
        return verified;
    }
}
