package org.mycompany.controllers.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Redirect servlet. Forward to resetPassword.jsp
 */
@WebServlet(name = "/resetPassword", urlPatterns = "/resetPassword")
public class ResetPasswordRedirectServlet extends HttpServlet{

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param httpServletRequest servlet request
     * @param httpServletResponse servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest, httpServletResponse);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param httpServletRequest servlet request
     * @param httpServletResponse servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/resetPassword.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

}
