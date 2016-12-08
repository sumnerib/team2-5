/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import java.util.Map;
import db.DBQueryBean;
//import db.QueryResult;
import java.io.*;
import java.sql.*;

/**
 *
 * @author Isaac Sumner
 */
public class FeedAction{
    
    
    private String question;
    
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
        //Map session = (Map) ActionContext.getContext().get("session");
        
        qid = result.getInt("max") + 1;
        
        /*String insert = "INSERT INTO questions(questionId, question, memberId)"
                + " VALUES (" + qid + ", " + question + ", " 
                + session.get("memberId") + ");";
        
        db.doQuery(insert);*/
    }
}
