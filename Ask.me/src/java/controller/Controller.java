/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Isaac Sumner
 */
@WebServlet(name = "Controller", urlPatterns = {"/control"})
public class Controller extends HttpServlet{
    
    /**
     * Handle an HTTP POST transaction 
     *
     * @param request The HTTP request object
     * @param response The HTTP response object
     */
    public void service(HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {
        
        String action = request.getParameter("action");
        
        switch (action) {
            
            case "login":
                forwardTo("/login.jsp", request, response);
                break;
                
            case "r]gegister":
                forwardTo("/register.jsp", request, response);
                break;
                
            case "feed":
                forwardTo("/feed.jsp", request, response);
                break;
                
            case "error":
                forwardTo("/error.jsp", request, response);
                break;
                
            case "profile":
                forwardTo("/profile.jsp", request, response);
                break;
                
            default:
                forwardTo("/", request, response);
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
     *  Clean input by removing quotes, and optionally blanks.
     * 
     * @param input The input to be cleaned
     * @param removeBlanks If true, all blanks are removed from the input
     * @return A cleaned version of the input
     */
    private String filter(String input, boolean removeBlanks) {
        if (input != null) {
            input = input.replace("'", "").replace("\"", "");
            if (removeBlanks) {
                input = input.replace(" ", "");
            }
        }
        return input;
    }
}
