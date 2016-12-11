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
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumnerib
 */
@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {

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
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        HttpSession session = request.getSession();

        switch (type) {

            case "question": {
                String command1 = "DELETE FROM questions WHERE questionId = "
                        + id;
                String command2 = "DELETE FROM answers WHERE questionId = "
                        + id;
                
                db.doUpdate(command2);
                db.doUpdate(command1);
                request.setAttribute("topBar", "<div class=\"alert alert-success\" role=\"alert\">\n"
                        + "  <strong>Done!</strong> Question #" + id + " has been deleted."
                        + "</div>");
                forwardTo("/feed.jsp", request, response);
            }
            break;
            case "answer": {
                int questionId = (Integer)session.getAttribute("questionId");

                String command = "DELETE FROM answers WHERE answerId = "
                        + id;

                db.doUpdate(command);
                request.setAttribute("topBar", "<div class=\"alert alert-success\" role=\"alert\">\n"
                        + "  <strong>Done!</strong> Answer #"+id+" has been deleted."
                        + "</div>");
                forwardTo("/answer?questionId="+questionId, request, response);
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

}
