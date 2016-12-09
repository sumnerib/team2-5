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
 * @author trippkm
 */
@WebServlet(name = "Feed", urlPatterns = {"/feed"})
public class Feed extends HttpServlet {

    private String yourQuestion;
    private DBQueryBean db;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Feed</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Feed at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
            throws ServletException, IOException 
    {
         db = new DBQueryBean();
         yourQuestion = request.getParameter("yourQuestion");
           
         if(yourQuestion != null && !"".equals(yourQuestion))
         {
             try {
                insertData();
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
             
             forwardTo("/feed.jsp", request, response);
         }
        
         forwardTo("/feed.jsp", request, response);
    }

    private void insertData() throws SQLException
    {       
        
        String insert = "INSERT INTO ??? VALUES ();";
        
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
