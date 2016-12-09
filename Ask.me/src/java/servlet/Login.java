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
import javax.servlet.http.HttpSession;
import db.DBQueryBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author trippkm
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private String username;
    private String password;
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
            throws ServletException, IOException {
        
        db = new DBQueryBean();
        HttpSession session = request.getSession();
        username = request.getParameter("username");
        password = request.getParameter("password");
        
        try {
            
            if (db.verifyCred(username, password)) {
                
                session.setAttribute("loggedin", true);
                session.setAttribute("userid", username);
                forwardTo("/feed.jsp", request, response);
            }
            else {
                request.setAttribute("errorMessage", "<div class=\"alert alert-danger\" role=\"alert\">\n" +
            "  <strong>Oh snap!</strong> Invalid username or password." +
            "</div>");
                forwardTo("/login.jsp", request, response);
            }
        }
        catch(SQLException sql) {
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
}
