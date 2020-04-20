package org.servlets.servlet;

import org.servlets.filter.AuthFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by MadYeti on 10.04.2020.
 */
@WebServlet(name = "/LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute("password");
        httpSession.removeAttribute("login");
        httpSession.removeAttribute("role");
        httpServletResponse.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

}
