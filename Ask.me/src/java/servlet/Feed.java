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

    public static final Logger LOG
            = Logger.getLogger("org.netbeans.modules.foo");
    private String yourQuestion;
    private DBQueryBean db;

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
        db = new DBQueryBean();
        yourQuestion = request.getParameter("yourQuestion");
        LOG.log(Level.INFO, "********YOUR QUESTION " + yourQuestion + "********");

        if (yourQuestion != null && !(yourQuestion.equals(""))) {
            LOG.log(Level.INFO, "********SHOULD BE UPDATING DB********");
            try {
                insertData(request.getSession());
                request.setAttribute("topBar", "<div class=\"alert alert-success alert-dismissable fade in\" role=\"alert\">\n"
                        + "  <strong>Yes!</strong> Your question has been posted."
                        + "</div>");
            } catch (SQLException ex) {
                //Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            forwardTo("/feed.jsp", request, response);
        } else {
            LOG.log(Level.INFO, "*******NOT UPDATING DB**********");
            request.setAttribute("topBar", "<div class=\"alert alert-danger alert-dismissable fade in\" role=\"alert\">\n"
                        + "  <strong>No!</strong> Something wrong went."
                        + "</div>");
            forwardTo("/feed.jsp", request, response);
        }
    }

    private void insertData(HttpSession session) throws SQLException {
        int qid;
        String getMax = "SELECT MAX(questionId) AS max FROM questions;";
        ResultSet result = db.doQuery(getMax);
        result.next();
        qid = result.getInt(1) + 1;

        db.addQuestion(qid, yourQuestion, (String) session.getAttribute("userid"));
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
