/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import db.DBQueryBean;

/**
 *
 * @author Isaac
 */
@WebServlet(name = "Answer", urlPatterns = {"/answer"})
public class Answer extends HttpServlet {
    private int questionId;
    private String question;
    private String username;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBQueryBean db = new DBQueryBean();
        questionId = Integer.parseInt(request.getParameter("questionId"));

        String query = "SELECT question, memberId FROM questions WHERE questionId = "
                + questionId;
        ResultSet results = db.doQuery(query);

        try {
            results.next();
            question = results.getString("question");
            request.setAttribute("question", question);
            int memberId = results.getInt("memberId");
            String memQuery = "SELECT username FROM members WHERE memberId = " + memberId;
            ResultSet member = db.doQuery(memQuery);
            member.next();
            username = member.getString(1);
            request.setAttribute("asker", username);
            request.setAttribute("questionId", questionId);
            forwardTo("/answer.jsp", request, response);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

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
            throws ServletException, IOException {

        String answer = request.getParameter("yourAnswer");
        DBQueryBean db = new DBQueryBean();
        int aid;
        
        questionId = Integer.parseInt(request.getParameter("questionId"));
        request.setAttribute("question", question);
        request.setAttribute("asker", username);
        

        try {
            // Increment answerId
            String getMax = "SELECT MAX(answerId) AS max FROM answers;";
            ResultSet result = db.doQuery(getMax);
            result.next();
            aid = result.getInt(1) + 1;

            db.addAnswer(aid, answer, questionId,
                    (String) request.getSession().getAttribute("userid"));
            request.setAttribute("topBar", "<div class=\"alert alert-success\" role=\"alert\">\n" +
            "  <strong>Yes!</strong> Your answer has been posted." +
            "</div>");
            forwardTo("/answer.jsp", request, response);
            response.sendRedirect("/Ask.me/answer.jsp");
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

    }

    /**
     * Forward a request to another component.
     *
     * @param url The url of the component to forward to
     * @param request The HttpRequest object
     * @param response The HttpResponse object
     */
    private void forwardTo(String url, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
