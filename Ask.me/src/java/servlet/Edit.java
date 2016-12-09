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
import db.DBQueryBean;
import java.util.HashMap;

/**
 *
 * @author zometaax
 */
@WebServlet(name = "Edit", urlPatterns = {"/edit"})
public class Edit extends HttpServlet {

    private DBQueryBean db;
    
    private String newUsername;
    private String newPassword;
    private String newName;
    private String newDOBDay;
    private String newDOBMonth;
    private String newDOBYear;
    private String newGender;
    private String newPhoto;

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
        
        newUsername = request.getParameter("newUsername");
        newPassword = request.getParameter("newPassword");
        newName = request.getParameter("newName");
        newDOBDay = request.getParameter("newDOBDay");
        newDOBMonth = request.getParameter("newDOBMonth");
        newDOBYear = request.getParameter("newDOBYear");
        newGender = request.getParameter("newGender");
        newPhoto = request.getParameter("newPhoto");
        
        map.put("Username", newUsername);
        map.put("Password", newPassword);
        map.put("Name", newName);
        map.put("DOBDay", newDOBDay);
        map.put("DOBMonth", newDOBMonth);
        map.put("DOBYear", newDOBYear);
        map.put("Gender", newGender);
        map.put("Photo", newPhoto);
        
        //db.editMember(map);
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