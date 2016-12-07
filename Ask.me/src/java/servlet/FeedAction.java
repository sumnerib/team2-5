/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
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
        
        try {
            saveQuestion(db);
        }
        catch (SQLException sql) {
            return ERROR;
        }
        
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
    private void saveQuestion(DBQueryBean db) throws SQLException {
        
        // Get the neccessary info to build the query string
        int qid;
        String getMax = "SELECT MAX(questionId) AS max FROM questions;";
        ResultSet result = db.doQuery(getMax);
        Map session = (Map) ActionContext.getContext().get("session");
        
        qid = result.getInt("max") + 1;
        
        String insert = "INSERT INTO questions(questionId, question, memberId)"
                + " VALUES (" + qid + ", " + question + ", " 
                + session.get("memberId") + ");";
        
        db.doQuery(insert);
    }
}
