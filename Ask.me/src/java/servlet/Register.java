/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBQueryBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saudalhilali
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {
    
        private String firstname;
        private String lastname;
        private String username;
        private String email;
        private String password;
        private String confirmPassword;
        private String dob;
        private String gender;
        private DBQueryBean db;


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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        db = new DBQueryBean();
        
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        username = request.getParameter("username");
        email = request.getParameter("email");
        password = request.getParameter("password");
        confirmPassword = request.getParameter("confirm_password");
        dob = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
        gender = request.getParameter("gender");
        
        if (gender.equals("M")) gender = "m";
        else gender = "f";
        
        if (db.checkForUsername(username)) {
            request.setAttribute("errorMessage", "<div class=\"alert alert-danger\" role=\"alert\">\n" +
            "  <strong>Oh snap!</strong> Username already exists." +
            "</div>");
            forwardTo("/register.jsp", request, response);
        }
        
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "<div class=\"alert alert-danger\" role=\"alert\">\n" +
            "  <strong>Oh snap!</strong> Password must match." +
            "</div>");
            forwardTo("/register.jsp", request, response);
        }
        
        if (!db.checkForUsername(username)) {
        
            try {
                insertData();
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }

            forwardTo("/index.html", request, response);
        } 
        
        
    }
    
    private void insertData() throws SQLException
    {
        
        int mid;
        String getMax = "SELECT MAX(memberId) AS max FROM members;";
        ResultSet result = db.doQuery(getMax);
        result.next();
        mid = result.getInt(1) + 1;
        
        String insert = "INSERT INTO members VALUES ('" + mid + "', '" + firstname+ " " + lastname + "', '0', '" +
                username + "', '1999-12-12', '" + gender + "', NULL, '" + password + "');";
        db.addMember(mid,firstname+" "+lastname, username, dob, gender, "a", password);
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

}
