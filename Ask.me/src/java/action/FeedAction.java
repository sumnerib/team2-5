/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static com.opensymphony.xwork2.Action.SUCCESS;
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
 * @author Isaac Sumner
 */
public class FeedAction extends ActionSupport{
    
    
    private String question;
    
    public void validate() {
        
        if (question == null || question.equals(""))
            addFieldError("question", "Question cannot be left blank!");
    }
    
    public String execute() {
        DBQueryBean db = new DBQueryBean();
        saveQuestion(db);
        return SUCCESS;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getQuestion() {
        return question;
    }
    
    /**
     * This method stores the question in the database
     */
    private void saveQuestion(DBQueryBean db) {
        
        String getMax = "SELECT MAX(questionId) AS max FROM questions;";
        ResultSet result = db.doQuery(getMax);
        
        try {
            int id = result.getInt("max") + 1;
        }
        catch (SQLException sql) {
            sql.printStackTrace();
        }
        
        String insert = "INSERT INTO questions(question)"
    }
}
