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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saudalhilali
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {
    
        String firstname;
        String lastname;
        String username;
        String email;
        String password;
        String month;
        String day;
        String year;
        String gender;


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
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        username = request.getParameter("username");
        email = request.getParameter("email");
        password = request.getParameter("password");
        month = request.getParameter("month");
        day = request.getParameter("day");
        year = request.getParameter("year");
        gender = request.getParameter("gender");
        if (gender.equals("male")) gender = "m";
        else gender = "f";
        
        try {
            insertData();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        forwardTo("/index.html", request, response);
    }
    
    private void insertData() throws SQLException
    {
        DBQueryBean db = new DBQueryBean();
        int mid;
        String getMax = "SELECT MAX(memberId) AS max FROM members;";
        ResultSet result = db.doQuery(getMax);
        result.next();
        mid = result.getInt(1) + 1;
        
        mid = 1;
        ArrayList<Object> list = new ArrayList<Object>();
        
        String insert = "INSERT INTO members VALUES ('" + mid + "', '" + firstname+ " " + lastname + "', '0', '" +
                username + "', '1999-12-12', '" + gender + "', NULL, '" + password + "');";
        db.addMember(mid,firstname+" "+lastname, username, "1999-1-1", gender, "a", password);
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
