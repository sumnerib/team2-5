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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saudalhilali
 */
@WebServlet(name = "Forgot", urlPatterns = {"/forgot"})
public class Forgot extends HttpServlet {

    private DBQueryBean db;
    private String username;
    private String newPassword;
    private String newConPassword;
    private String secQuestion;
    private String secAnswer;

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
        HashMap<String, String> map = new HashMap();

        db = new DBQueryBean();

        username = request.getParameter("username");
        newPassword = request.getParameter("newPassword");
        secQuestion = request.getParameter("secQuestion");
        secAnswer = request.getParameter("secAnswer");
        HttpSession session = request.getSession();
        

        switch (request.getParameter("type")) {
            case "checkQA": {
                String query = "SELECT secQuestion, secAnswer FROM members WHERE username = '" + username + "'";

                try {
                    ResultSet result = db.doQuery(query);
                    result.next();
                    if (result.getString("secQuestion").equalsIgnoreCase(secQuestion)
                            && result.getString("secAnswer").equalsIgnoreCase(secAnswer)) {
                        request.setAttribute("errorMessage", "<div class=\"alert alert-success\" role=\"alert\">\n"
                                + "  <strongYes!</strong> Now you can update your password."
                                + "</div>");
                        session.setAttribute("formPass", true);
                        session.setAttribute("username", username);
                        forwardTo("/setNewPassword.jsp", request, response);
                    } else {
                        request.setAttribute("errorMessage", "<div class=\"alert alert-danger\" role=\"alert\">\n"
                                + "  <strong>No!</strong> Your question or answer is wrong."
                                + "</div>");
                        forwardTo("/forgot.jsp", request, response);
                    }
                } catch (SQLException sql) {
                    request.setAttribute("errorMessage", "<div class=\"alert alert-danger\" role=\"alert\">\n"
                            + "  <strong>No!</strong> Your question or answer is wrong."
                            + "</div>");
                    sql.printStackTrace();
                    forwardTo("/forgot.jsp", request, response);
                }
            try {
                db.closeCon();
            } catch (SQLException ex) {
                Logger.getLogger(Forgot.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            break;
            case "updatePass": {

                DBQueryBean dbb = new DBQueryBean();

                newPassword = request.getParameter("newPassword");
                newConPassword = request.getParameter("conNewPassword");

                if (!newPassword.equals(newConPassword)) {
                    request.setAttribute("errorMessage", "<div class=\"alert alert-danger\" role=\"alert\">\n"
                            + "  <strongSnap!</strong> Your new password must match."
                            + "</div>");
                    forwardTo("/setNewPassword.jsp", request, response);

                } else {
                    db.updatePassword((String)session.getAttribute("username"), newPassword);
                    request.setAttribute("errorMessage", "<div class=\"alert alert-success\" role=\"alert\">\n"
                            + "  <strongYes!</strong> Your new password has been updated. Log in now."
                            + "</div>");
                    try {
                        dbb.closeCon();
                    } catch (SQLException ex) {
                        Logger.getLogger(Forgot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    forwardTo("/login.jsp", request, response);

                }

            }
            break;
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
