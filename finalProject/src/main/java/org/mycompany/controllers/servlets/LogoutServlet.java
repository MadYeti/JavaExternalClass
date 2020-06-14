package org.mycompany.controllers.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doPost(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession != null) {
            httpServletRequest.removeAttribute("client");
            httpSession.invalidate();
        }
        requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/index.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
