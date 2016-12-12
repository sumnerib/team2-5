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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servlet.Feed.LOG;

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
    public static final Logger LOG
            = Logger.getLogger("org.netbeans.modules.foo");

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
        newPassword = db.hashPassword(request.getParameter("newPassword"));
        newName = request.getParameter("newName");
        newDOBDay = request.getParameter("newDOBDay");
        newDOBMonth = request.getParameter("newDOBMonth");
        newDOBYear = request.getParameter("newDOBYear");
        newGender = request.getParameter("newGender");
        newPhoto = request.getParameter("newPhoto");
        
        map.put("username", newUsername);
        map.put("pass", newPassword);
        map.put("name", newName);
        //map.put("dob", "2012-12-21");
        if (!newGender.equalsIgnoreCase("")) map.put("gender", newGender.substring(0, 1));
        map.put("image", newPhoto);
        
        LOG.log(Level.INFO, "********YOUR DOB " + newDOBYear + "-" + newDOBMonth + "-" + newDOBDay + "********");
        
        if ((newDOBYear != null && !newDOBYear.equals(""))
                && (newDOBMonth != null && !newDOBMonth.equals(""))
                && (newDOBDay != null && !newDOBDay.equals(""))) {

            map.put("dob", newDOBYear + "-" + newDOBMonth + "-" + newDOBDay);
        }

        try {
            db.editMember(map,
                    (String) request.getSession().getAttribute("userid"));

            request.setAttribute("topBar", "<div class=\"alert alert-success\" role=\"alert\">\n"
                    + "  <strongYes!</strong> Your new changes have been updated."
                    + "</div>");
            //db.editMember(map, "sumnerib");
        } catch (SQLException sql) {
            request.setAttribute("topBar", "<div class=\"alert alert-danger\" role=\"alert\">\n"
                    + "  <strong>No!</strong> Something wrong went."
                    + "</div>");
            sql.printStackTrace();
        }
        
        try {
            db.closeCon();
        }
        catch (SQLException sql) {
            sql.printStackTrace();
        }
        forwardTo("/profile.jsp", request, response);
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
