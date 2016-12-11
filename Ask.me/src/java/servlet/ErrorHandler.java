/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBQueryBean;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
@WebServlet(name = "ErrorHandler", urlPatterns = {"/ErrorHandler"})
public class ErrorHandler extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            String name = null;
            String uriTemp = (String) request.getAttribute("javax.servlet.error.request_uri");
            name = uriTemp.substring(uriTemp.indexOf(getServletContext().getContextPath()) + getServletContext().getContextPath().length() + 1);

            String query = "SELECT * FROM members WHERE username = '" + name + "'";
            DBQueryBean db = new DBQueryBean();
            ResultSet resultSet = db.doQuery(query);
            if (resultSet.next()) {
                session.setAttribute("memberProfile", name);
                forwardTo("/member.jsp", request, response);
            } else {
                response.sendRedirect("/Ask.me/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();

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
