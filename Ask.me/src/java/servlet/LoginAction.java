/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import db.DBQueryBean;
//import db.QueryResult;
import java.io.*;
import java.sql.*;

/**
 *
 * @author trippkm
 */
public class LoginAction extends ActionSupport 
{
    private String username;
    private String passwd;
    
    public void validate() 
    {
        
        if (username == null || username.equals(""))
            addFieldError("username", "The username cannot be blank");
        
        if (passwd == null || passwd.equals(""))
            addFieldError("passwd", "The password cannot be blank");
                    
        
            
        /*        
        Map session = ActionContext.getContext().getSession();
        session.put("logined", "true");
        session.put("context", new Date());
        return SUCCESS;
            
        
        return ERROR;
        */
    }
    
    public String execute() {
        
        DBQueryBean db = new DBQueryBean();
        
        try {
            if (!verifyCred(db))
                return INPUT;
        }
        catch (SQLException sql) {
            return ERROR;
        }
        
        return SUCCESS;
    }
    
    public String logout() throws Exception
    {
        Map session = ActionContext.getContext().getSession();
        session.remove("logined");
        session.remove("context");
        return SUCCESS;
    }
    
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
