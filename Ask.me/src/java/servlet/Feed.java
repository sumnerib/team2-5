/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBQueryBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trippkm
 */
@WebServlet(name = "Feed", urlPatterns = {"/feed"})
public class Feed extends HttpServlet {

    private String yourQuestion;
    private DBQueryBean db;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
         db = new DBQueryBean();
         yourQuestion = request.getParameter("yourQuestion");
           
         if(yourQuestion != null && !"".equals(yourQuestion))
         {
             try {
                insertData(request.getSession());
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
             
             forwardTo("/feed.jsp", request, response);
         }
        
         forwardTo("/feed.jsp", request, response);
    }

    private void insertData(HttpSession session) throws SQLException
    {       
        int qid;
        String getMax = "SELECT MAX(questionId) AS max FROM questions;";
        ResultSet result = db.doQuery(getMax);
        result.next();
        qid = result.getInt(1) + 1;
        
        db.addQuestion(qid, yourQuestion, (String)session.getAttribute("userid"));
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

      private void forwardTo(String url, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

}
